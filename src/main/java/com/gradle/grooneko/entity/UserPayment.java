package com.gradle.grooneko.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class UserPayment implements Serializable {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    private String cardNum;

    private int CCV;
    private String expDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
