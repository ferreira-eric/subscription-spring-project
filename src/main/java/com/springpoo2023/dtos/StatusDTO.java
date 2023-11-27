package com.springpoo2023.dtos;

import com.springpoo2023.utils.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class StatusDTO implements Serializable {

    private static final long serialVersionUID = 1113L;

    private UUID id;

    private StatusEnum statusName;
}
