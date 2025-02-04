package com.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class SubscriptionDTO implements Serializable {

    private static final long serialVersionUID = -7459825067072894961L;

    private UUID id;

    @NotNull
    private UUID userId;

    private UUID statusId;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;
}
