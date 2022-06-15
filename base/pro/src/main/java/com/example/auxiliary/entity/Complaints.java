package com.example.auxiliary.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author ztx
 * @date 2021-02-21 13:31
 */
@Data
@SuperBuilder
@RequiredArgsConstructor
public class Complaints {

    private String id;
    private String userId;
    private String contents;
    private String status;

    @TableField(exist = false)
    private String nickName;
    @TableField(exist = false)
    private String phone;
}
