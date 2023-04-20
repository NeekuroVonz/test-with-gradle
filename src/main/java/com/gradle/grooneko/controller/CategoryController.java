package com.gradle.grooneko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gradle.grooneko.entity.Category;
import com.gradle.grooneko.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public List<Category> findAll() {
        return categoryService.getAll();
    }

    @GetMapping("/category/{id}")
    public Category findById(@PathVariable(value = "id") String id) {
        return categoryService.findById(id);
    }

    @PostMapping("/category")
    public Category create(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @PatchMapping("/category/{id}")
    public Category update(@PathVariable(value = "id") String id, @RequestBody Category category) {
        return categoryService.update(id, category);
    }

    @DeleteMapping("/category/{id}")
    public void deleteById(@PathVariable(value = "id") String id) {
        categoryService.deleteById(id);
    }
}
