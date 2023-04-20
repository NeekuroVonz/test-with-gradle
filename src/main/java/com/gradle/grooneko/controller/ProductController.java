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

import com.gradle.grooneko.entity.Product;
import com.gradle.grooneko.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/product/{id}")
    public Product findById(@PathVariable(value = "id") String id) {
        return productService.findById(id);
    }

    @PostMapping("/product")
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PatchMapping("/product/{id}")
    public Product update(@PathVariable(value = "id") String id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/product/{id}")
    public void delete(@PathVariable(value = "id") String id) {
        productService.delete(id);
    }
}
