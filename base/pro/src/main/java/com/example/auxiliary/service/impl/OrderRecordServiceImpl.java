package com.example.auxiliary.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.auxiliary.cumenum.OrderStatusEnum;
import com.example.auxiliary.entity.OrderRecord;
import com.example.auxiliary.entity.Result;
import com.example.auxiliary.entity.User;
import com.example.auxiliary.mapper.OrderRecordMapper;
import com.example.auxiliary.service.IOrderRecordService;
import com.example.auxiliary.service.IUserCommentService;
import com.example.auxiliary.service.IUserService;
import com.google.common.base.Enums;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ztx
 * @date 2021-02-21 13:34
 */
@Service
@RequiredArgsConstructor
public class OrderRecordServiceImpl extends ServiceImpl<OrderRecordMapper, OrderRecord> implements IOrderRecordService {

    private final OrderRecordMapper orderRecordMapper;
    private final IUserService userService;


    /**
     * 获取所有订单记录
     *
     * @return 订单记录
     */
    @Override
    public Result<List<OrderRecord>> getAllOrderRecord() {
        List<OrderRecord> allOrderRecord = orderRecordMapper.getAllOrderRecord();
        if (CollectionUtils.isNotEmpty(allOrderRecord)) {
            allOrderRecord.forEach(orderRecord -> {
                User user = this.userService.getById(orderRecord.getUserId());
                orderRecord.setStatusName(OrderStatusEnum.getNameByValue(orderRecord.getStatus()));
                orderRecord.setPhone(user.getPhone());
            });
        }
        return Result.createSuccess(allOrderRecord);
    }
}
