package com.gradle.grooneko.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gradle.grooneko.entity.Category;
import com.gradle.grooneko.exception.BadRequestException;
import com.gradle.grooneko.exception.ResourceNotFoundException;
import com.gradle.grooneko.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category findById(String id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Category with id: " + id + " not found!"));
    }

    public Category create(Category category) {
        category.setId(UUID.randomUUID().toString());
        
        if (!StringUtils.hasText(category.getName())) {
            throw new BadRequestException("Category name cannot be blank!");
        }

        if (categoryRepository.findByName(category.getName()) != null &&  categoryRepository.findByName(category.getName()).getName().equalsIgnoreCase(category.getName())) {
            throw new BadRequestException("Category name is exist please name another!");
        }

        category.setCreated_at(LocalDateTime.now());
        category.setUpdated_at(category.getCreated_at());

        return categoryRepository.save(category);
    }

    public Category update(String id, Category category) {
        Category getCate = categoryRepository.findById(id)
                                .orElseThrow(() -> new BadRequestException("Category with id: " + id + " not exist!"));

        if (categoryRepository.findByName(category.getName()) != null &&  categoryRepository.findByName(category.getName()).getName().equalsIgnoreCase(category.getName())) {
            throw new BadRequestException("Category name is exist please name another!");
        }
        getCate.setName(category.getName());
        getCate.setUpdated_at(LocalDateTime.now());

        return categoryRepository.save(getCate);
    }

    public void deleteById(String id) {
        categoryRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("Category with id: " + id + " not found!"));

        categoryRepository.deleteById(id);
    }
}
