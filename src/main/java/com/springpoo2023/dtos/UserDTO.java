package com.springpoo2023.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 200359717740034318L;

    private UUID id;

    @NotBlank
    private String fullName;

    private LocalDateTime createdAt;
}
