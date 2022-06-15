package com.example.auxiliary.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.auxiliary.cumenum.OrderStatusEnum;
import com.example.auxiliary.entity.Goods;
import com.example.auxiliary.entity.OrderRecord;
import com.example.auxiliary.entity.Result;
import com.example.auxiliary.service.IGoodsService;
import com.example.auxiliary.service.IOrderRecordService;
import com.example.auxiliary.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 订单接口
 *
 * @author ztx
 * @date 2021-05-23 23:13
 */
@RequestMapping("/orderRecord")
@RestController
@RequiredArgsConstructor
public class OrderRecordController {

    private final IOrderRecordService orderRecordService;
    private final IGoodsService goodsService;
    private final IUserService userService;

    /**
     * 新增或更改订单
     *
     * @param orderRecords 订单记录
     * @return 订单记录
     */
    @PostMapping("/orderRecord")
    public Result<List<OrderRecord>> save(@RequestBody List<OrderRecord> orderRecords) {
        String orderId = UUID.randomUUID().toString();
        orderRecords.forEach(orderRecord -> {
            if (Objects.isNull(orderRecord.getCreateDatetime())) {
                orderRecord.setCreateDatetime(LocalDateTime.now());
                orderRecord.setOrderId(orderId);
            }
        });
        this.orderRecordService.saveOrUpdateBatch(orderRecords);
        return Result.createSuccess(orderRecords);
    }

    /**
     * 获取订单记录
     *
     * @param userId 用户id
     * @param status 状态
     * @return 订单记录
     */
    @GetMapping("/list")
    public Result<Map<String, List<OrderRecord>>> getOrderRecord(@RequestParam(required = false) String userId, @RequestParam(required = false) String status) {
        List<OrderRecord> orderRecordList = this.orderRecordService.list(Wrappers.<OrderRecord>lambdaQuery()
            .eq(StringUtils.isNotBlank(userId), OrderRecord::getUserId, userId)
            .eq(StringUtils.isNotBlank(status), OrderRecord::getStatus, status));
        orderRecordList.forEach(item -> {
            item.setGood(this.goodsService.getById(item.getGoodsId()));
            item.setUser(this.userService.getById(item.getUserId()));
        });
        //  根据订单号聚合一下订单
        Map<String, List<OrderRecord>> collect = orderRecordList.stream().collect(Collectors.groupingBy(OrderRecord::getOrderId));
        return Result.createSuccess(collect);
    }

    /**
     * 获取所有订单记录（后台管理员用）
     *
     * @return 订单
     */
    @GetMapping("/allOrderRecord")
    public Result<List<OrderRecord>> getAllOrderRecord() {
        return this.orderRecordService.getAllOrderRecord();
    }
}
