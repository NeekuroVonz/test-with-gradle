package com.gradle.grooneko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gradle.grooneko.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
    
    @Query("SELECT u FROM Category u WHERE u.name = :name")
    Category findByName(@Param("name") String name);

}
