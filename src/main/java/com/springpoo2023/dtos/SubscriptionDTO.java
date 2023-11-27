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

    private static final long serialVersionUID = 1112L;
    @NotBlank
    private UUID id;

    @NotBlank
    private UUID userId;

    @NotBlank
    private UUID statusId;

    @NotBlank
    private LocalDateTime createdAt;

    @NotBlank
    private LocalDateTime updateAt;
}
