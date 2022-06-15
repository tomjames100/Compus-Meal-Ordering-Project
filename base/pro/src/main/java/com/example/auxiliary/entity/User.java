package com.example.auxiliary.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author ztx
 * @date 2021-02-21 13:31
 */
@Data
@SuperBuilder
@RequiredArgsConstructor
public class User {

    private String id;
    private String name;
    private String phone;
    private String password;
    private String openId;
    private String gender;
    private String nickName;
    private String province;
    private String avatarUrl;
}
