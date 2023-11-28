package com.springpoo2023.dtos;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private UUID userId;

    private UUID statusId;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;
}
