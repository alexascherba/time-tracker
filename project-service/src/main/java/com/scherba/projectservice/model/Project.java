package com.scherba.projectservice.model;

import jakarta.persistence.*;
import lombok.Data;

//Сущность, которая отображает таблицу projects в бд

@Entity
@Table(name = "projects")
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}
