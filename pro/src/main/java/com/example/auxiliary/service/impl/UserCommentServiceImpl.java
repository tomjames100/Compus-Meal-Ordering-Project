package com.example.auxiliary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.auxiliary.entity.UserComment;
import com.example.auxiliary.mapper.UserCommentMapper;
import com.example.auxiliary.service.IUserCommentService;
import org.springframework.stereotype.Service;

/**
 * @author ztx
 * @date 2021-02-21 13:34
 */
@Service
public class UserCommentServiceImpl extends ServiceImpl<UserCommentMapper, UserComment> implements IUserCommentService {
}
