package com.springpoo2023.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Event_History")
public class EventHistory {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID subscriptionId;

    private String type;

    @CreatedDate
    private LocalDateTime createdAt;

}
