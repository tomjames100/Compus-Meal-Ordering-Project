package com.example.auxiliary.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author ztx
 * @date 2021-05-23 23:11
 */
@Data
@SuperBuilder
@RequiredArgsConstructor
public class Admin {
    private String id;
    private String username;
    private String password;
}
