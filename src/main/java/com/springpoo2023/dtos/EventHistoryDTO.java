package com.springpoo2023.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.mvc.LastModified;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class EventHistoryDTO implements Serializable {

    private static final long serialVersionUID = -1432268253557650235L;

    private UUID id;

    private UUID subscriptionId;

    private String type;

    private LocalDateTime createdAt;

}
