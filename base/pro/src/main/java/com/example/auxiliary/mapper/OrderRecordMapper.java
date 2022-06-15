package com.example.auxiliary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.auxiliary.entity.OrderRecord;
import com.example.auxiliary.entity.User;

import java.util.List;

public interface OrderRecordMapper extends BaseMapper<OrderRecord> {

    List<OrderRecord> getAllOrderRecord();
}
