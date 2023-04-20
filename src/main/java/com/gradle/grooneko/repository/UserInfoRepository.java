package com.gradle.grooneko.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gradle.grooneko.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    
}
