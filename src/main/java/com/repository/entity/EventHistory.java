package com.repository.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Event_History")
public class EventHistory {

    private static final long serialVersionUID = 654304531482054132L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID subscriptionId;

    private String type;

    @CreatedDate
    private LocalDateTime createdAt;

}
