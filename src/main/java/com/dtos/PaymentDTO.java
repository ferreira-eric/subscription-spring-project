package com.dtos;

import com.utils.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5996483765299261439L;

    private Long id;

    @NotNull
    private Long subscriptionId;

    @NotNull
    private Long userId;

    @NotNull
    private BigDecimal amount;

    private PaymentStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
