package com.springpoo2023.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.mvc.LastModified;


import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class EventHistoryDTO implements Serializable {

    private static final long serialVersionUID = 1114L;

    private UUID id;

    private UUID subscriptionId;

    private String type;

    private LastModified createdAt;

}
