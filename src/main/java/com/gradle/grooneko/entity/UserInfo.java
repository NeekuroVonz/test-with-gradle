package com.gradle.grooneko.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class UserInfo implements Serializable {
    @Id
    @Column(name = "user_id")
    private String id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    private String firstName;
    private String lastName;

    private String address;

    private String phone;
    private String image;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
