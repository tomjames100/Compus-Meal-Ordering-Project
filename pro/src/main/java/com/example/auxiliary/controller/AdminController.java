package com.example.auxiliary.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.auxiliary.entity.Admin;
import com.example.auxiliary.entity.Result;
import com.example.auxiliary.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author ztx
 * @date 2021-05-23 23:13
 */
@RequestMapping("/admin")
@RestController
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;


    /**
     * 登录接口
     *
     * @param username 用户名
     * @param password 密码
     * @return 是否登录成功
     */
    @GetMapping("/login")
    public Result<String> login(@RequestParam String username, @RequestParam String password) {
        Admin admin = adminService.getOne(Wrappers.<Admin>lambdaQuery()
            .eq(Admin::getUsername, username)
            .eq(Admin::getPassword, password));
        if (Objects.isNull(admin)) {
            return Result.createFail("账号或者密码不存在!");
        }
        return Result.createSuccess(admin.getUsername());
    }

    /**
     * 添加管理员
     *
     * @param admin 管理员账号
     * @return 结果
     */
    @PostMapping("/admin")
    public Result<Void> addAdmin(@RequestBody Admin admin) {
        Admin one = this.adminService.getOne(Wrappers.<Admin>lambdaQuery()
            .eq(Admin::getUsername, admin.getUsername()));
        this.adminService.saveOrUpdate(admin);
        return Result.createSuccess();
    }

    /**
     * 获取管理员列表
     *
     * @return 管理员列表
     */
    @GetMapping("/admin")
    Result<List<Admin>> getAdmins() {
        return Result.createSuccess(this.adminService.list());
    }

    /**
     * 删除管理员
     *
     * @param id 管理员id
     * @return 结果
     */
    @DeleteMapping("/{id}")
    Result<Void> delete(@PathVariable String id) {
        this.adminService.removeById(id);
        return Result.createSuccess();
    }
}
