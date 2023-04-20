package com.gradle.grooneko.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gradle.grooneko.entity.UserPayment;

public interface UserPaymentRepository extends JpaRepository<UserPayment, String> {
    
}
