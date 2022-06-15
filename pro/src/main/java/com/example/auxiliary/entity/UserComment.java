package com.example.auxiliary.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ztx
 * @date 2021-02-21 13:31
 */
@Data
@SuperBuilder
@RequiredArgsConstructor
public class UserComment {

    private String id;
    private String userId;
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String avatarUrl;
    private String orderId;
    private Integer star;
    private LocalDateTime createDatetime;
    private String contents;

    @TableField(exist = false)
    private String phone;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private List<String> goodsImageUrls;
}
