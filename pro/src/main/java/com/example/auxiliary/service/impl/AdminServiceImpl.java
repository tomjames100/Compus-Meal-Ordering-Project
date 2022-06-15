package com.example.auxiliary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.auxiliary.entity.Admin;
import com.example.auxiliary.mapper.AdminMapper;
import com.example.auxiliary.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * @author ztx
 * @date 2021-02-21 13:34
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {


}
