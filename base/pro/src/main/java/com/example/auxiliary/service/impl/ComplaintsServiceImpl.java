package com.example.auxiliary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.auxiliary.entity.Complaints;
import com.example.auxiliary.mapper.ComplaintsMapper;
import com.example.auxiliary.service.IComplaintsService;
import org.springframework.stereotype.Service;

/**
 * @author ztx
 * @date 2021-02-21 13:34
 */
@Service
public class ComplaintsServiceImpl extends ServiceImpl<ComplaintsMapper, Complaints> implements IComplaintsService {

}
