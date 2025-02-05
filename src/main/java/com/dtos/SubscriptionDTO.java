package com.dtos;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class SubscriptionDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7459825067072894961L;

    private UUID id;

    @NotNull
    private UUID userId;

    private UUID statusId;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    public static SubscriptionDTO deserialize(Object object){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return mapper.convertValue(object, SubscriptionDTO.class);
    }
}
