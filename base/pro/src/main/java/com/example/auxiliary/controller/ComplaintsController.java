package com.example.auxiliary.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.auxiliary.entity.Complaints;
import com.example.auxiliary.entity.Result;
import com.example.auxiliary.entity.User;
import com.example.auxiliary.entity.UserComment;
import com.example.auxiliary.service.IComplaintsService;
import com.example.auxiliary.service.IUserCommentService;
import com.example.auxiliary.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 投诉接口
 *
 * @author ztx
 * @date 2021-05-23 23:13
 */
@RequestMapping("/complaints")
@RestController
@RequiredArgsConstructor
public class ComplaintsController {

    private final IComplaintsService complaintsService;
    private final IUserService userService;

    /**
     * 提交投诉
     *
     * @param complaints 投诉内容
     * @return 结果
     */
    @PostMapping("/complaints")
    Result<?> commitComplains(@RequestBody Complaints complaints) {
        complaintsService.saveOrUpdate(complaints);
        return Result.createSuccess();
    }

    /**
     * 获取所有投诉
     *
     * @return 投诉信息
     */
    @GetMapping("/complaints")
    Result<List<Complaints>> getAllComplains(String keywords) {
        List<Complaints> list = this.complaintsService.list(Wrappers.<Complaints>lambdaQuery()
            .eq(StringUtils.isNotBlank(keywords), Complaints::getContents, keywords));
        list.forEach(item -> {
            User user = this.userService.getById(item.getUserId());
            item.setNickName(user.getNickName());
            item.setPhone(user.getPhone());

        });
        return Result.createSuccess(list);
    }

}
