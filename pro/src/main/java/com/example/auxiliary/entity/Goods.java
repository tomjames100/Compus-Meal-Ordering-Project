package com.example.auxiliary.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品实体
 *
 * @author ztx
 * @date 2021-05-23 23:11
 */
@Data
@SuperBuilder
@RequiredArgsConstructor
public class Goods {
    private String id;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private String category;
    private String status;
    private LocalDateTime createDatetime;
}
