package com.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEnum {

    SUBSCRIPTION_PURCHASED("PURCHASED"),

    SUBSCRIPTION_CANCELED("CANCELED"),

    SUBSCRIPTION_RESTARTED("RESTARTED");

    private final String value;
}
