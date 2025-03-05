package com.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class SubscriptionDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7459825067072894961L;

    private Long id;

    @NotNull
    private Long userId;

    private Long statusId;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;
}
