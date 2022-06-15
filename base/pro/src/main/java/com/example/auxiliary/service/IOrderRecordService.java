package com.example.auxiliary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.auxiliary.entity.OrderRecord;
import com.example.auxiliary.entity.Result;

import java.util.List;

public interface IOrderRecordService extends IService<OrderRecord> {

    Result<List<OrderRecord>> getAllOrderRecord();
}
