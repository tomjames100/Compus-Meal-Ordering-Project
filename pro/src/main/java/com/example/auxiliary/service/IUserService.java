package com.example.auxiliary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.auxiliary.entity.Result;
import com.example.auxiliary.entity.User;

public interface IUserService extends IService<User> {


    Result<?> createGroup(String groupName,String userIds);

}
