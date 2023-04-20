package com.gradle.grooneko.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Category implements Serializable {
    @Id
    private String id;

    private String name;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
