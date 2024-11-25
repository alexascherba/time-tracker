package com.scherba.trackerservice.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

//Сущность, которая отображает таблицу records в бд

@Data
@Entity
@Table(name = "records")
public class TimeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    // Хранение идентификатора пользователя из Keycloak
    @JoinColumn(name = "user_id", nullable = false)
    private String userId;
}

