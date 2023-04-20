package com.gradle.grooneko.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.gradle.grooneko.model.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User implements Serializable {
    @Id
    private String id;

    private String username;
    private String password;

    private String email;

    private UserRole role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
