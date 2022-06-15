package com.example.auxiliary.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.auxiliary.config.WxMaConfiguration;
import com.example.auxiliary.entity.Result;
import com.example.auxiliary.entity.ResultEnum;
import com.example.auxiliary.entity.User;
import com.example.auxiliary.service.IUserService;
import com.example.auxiliary.utils.JsonUtils;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.message.ReusableMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * 微信小程序用户接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RestController
@RequestMapping("/wx/user/{appid}")
public class WxMaUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserService userService;

    /**
     * 登陆接口（获取到openId及sessionKey）
     */
    @GetMapping("/login")
    public String login(@PathVariable String appid, String code) {
        if (StringUtils.isBlank(code)) {
            return "empty jscode";
        }
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());
            return JsonUtils.toJson(session);
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return e.toString();
        }
    }

    /**
     * 登录数据
     *
     * @param wxMaUserInfo
     * @return
     */
    @PostMapping("/wxLogin")
    public Result<?> wxLogin(@RequestBody WxMaUserInfo wxMaUserInfo) {
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getOpenId, wxMaUserInfo.getOpenId()));
        if (Objects.isNull(user)) {
            //插入数据
            user = User.builder()
                .id(UUID.randomUUID().toString())
                .gender(wxMaUserInfo.getGender())
                .nickName(wxMaUserInfo.getNickName())
                .openId(wxMaUserInfo.getOpenId())
                .province(wxMaUserInfo.getProvince())
                .avatarUrl(wxMaUserInfo.getAvatarUrl())
                .build();
            userService.saveOrUpdate(user);
        }
        return Result.createSuccess(user);
    }

    /**
     * 新增或修改用户信息
     *
     * @param user 用户信息
     * @return 用户信息
     */
    @PostMapping("/registerUser")
    public Result<?> registerUser(@RequestBody User user) {
        List<User> users = this.userService.list(Wrappers.<User>lambdaQuery().eq(User::getPhone, user.getPhone()));
        if (CollectionUtils.isNotEmpty(users)) {
            return Result.createFail("已存在改手机号的用户!");
        }
        this.userService.saveOrUpdate(user);
        return Result.createSuccess(user);
    }

    /**
     * 账号密码登录
     *
     * @param user 登录信息
     * @return 登录结果
     */
    @GetMapping("/phonePasswordLogin")
    public Result<User> phonePasswordLogin(User user) {
        List<User> users = this.userService.list(Wrappers.<User>lambdaQuery()
            .eq(User::getPhone, user.getPhone())
            .eq(User::getPassword, user.getPassword()));
        if (CollectionUtils.isEmpty(users)) {
            Result.createFail("账号或者密码错误!");
        }
        return Result.createSuccess(users.get(0));
    }
}
