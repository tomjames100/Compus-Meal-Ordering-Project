package com.example.auxiliary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.auxiliary.entity.Result;
import com.example.auxiliary.entity.User;
import com.example.auxiliary.mapper.UserMapper;
import com.example.auxiliary.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author ztx
 * @date 2021-02-21 13:34
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    /**
     * 创建分组
     * @param groupName
     * @param userIds
     * @return
     */
    @Override
    public Result<?> createGroup(String groupName, String userIds) {
        //todo 创建分组
        return null;
    }
}
