package com.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEnum {
    SUBSCRIPTION_WAITING_FOR_PAYMENT,
    SUBSCRIPTION_PURCHASED,
    SUBSCRIPTION_CANCELED,
    SUBSCRIPTION_RESTARTED
}
