package com.example.auxiliary.cumenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GoodsStatusEnum {
    ONLINE("上线", "a"),
    UNONLIE("下线", "b");

    private String name;
    private String value;

    public static String getNameByValue(String value) {
        GoodsStatusEnum[] businessModeEnums = values();
        for (GoodsStatusEnum businessModeEnum : businessModeEnums) {
            if (businessModeEnum.getValue().equals(value)) {
                return businessModeEnum.getName();
            }
        }
        return null;
    }
}
