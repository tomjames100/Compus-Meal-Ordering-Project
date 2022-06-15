package com.example.auxiliary.cumenum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IndexGoodTypeEnum {
    ALL("全部", "a"),
    HOT("热门推荐", "b"),
    CUSM("为您精选", "c"),
    RANK("排行榜", "d");

    private String name;
    private String value;
}
