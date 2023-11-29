package com.springpoo2023.repository.entity;

import com.springpoo2023.utils.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Status")
public class Status implements Serializable {

    private static final long serialVersionUID = 7473477978021073570L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private StatusEnum statusName;
}
