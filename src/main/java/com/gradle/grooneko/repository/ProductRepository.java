package com.gradle.grooneko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gradle.grooneko.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
    
    @Query("SELECT u FROM Product u WHERE u.name = :name")
    Product findByName(@Param("name") String name);
}
