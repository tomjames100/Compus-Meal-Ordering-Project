package com.example.auxiliary.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ztx
 * @date 2021-03-01 22:11
 */
@Data
@Builder
public class FileDto {
    private String id;
    private String openId;
    private String title;
    private String content;
    private String filePath;
    private String name;
    private LocalDateTime createTime;
    private String nickName;
}
