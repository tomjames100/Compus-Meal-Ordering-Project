package com.example.auxiliary.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单记录实体
 *
 * @author ztx
 * @date 2021-02-21 13:31
 */
@Data
@SuperBuilder
@RequiredArgsConstructor
public class OrderRecord {

    private String id;
    private String userId;
    private String goodsId;
    private Integer num;
    private BigDecimal price;
    private LocalDateTime createDatetime;
    private String status;
    private String orderId;

    @TableField(exist = false)
    private String statusName;

    @TableField(exist = false)
    private String storeName;
    @TableField(exist = false)
    private String title;
    @TableField(exist = false)
    private String cover;
    @TableField(exist = false)
    private String nickName;
    @TableField(exist = false)
    private String phone;
    @TableField(exist = false)
    private Goods good;
    @TableField(exist = false)
    private User user;
}
