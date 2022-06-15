package com.example.auxiliary.cumenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatusEnum {
    PAYED("已付款", "a"),
    COMPELTE("完成交易", "b");

    private String name;
    private String value;

    public static String getNameByValue(String value) {
        OrderStatusEnum[] businessModeEnums = values();
        for (OrderStatusEnum businessModeEnum : businessModeEnums) {
            if (businessModeEnum.getValue().equals(value)) {
                return businessModeEnum.getName();
            }
        }
        return null;
    }
}
