package com.gradle.grooneko.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gradle.grooneko.entity.Product;
import com.gradle.grooneko.exception.BadRequestException;
import com.gradle.grooneko.exception.ResourceNotFoundException;
import com.gradle.grooneko.repository.CategoryRepository;
import com.gradle.grooneko.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exist!"));
    }

    public Product create(Product product) {
        product.setId(UUID.randomUUID().toString());
        
        if (!StringUtils.hasText(product.getName())) {
            throw new BadRequestException("Product name cannot be blank!");
        }
        if (productRepository.findByName(product.getName()) != null && productRepository.findByName(product.getName()).getName().equalsIgnoreCase(product.getName())) {
            throw new BadRequestException("Product with name: " + product.getName() + " is already exist!");
        }
        if (product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Price can be smaller than 0 or equal to 0!");
        }
        if (product.getAmount() < 0) {
            throw new BadRequestException("Amount cannot be less than 0");
        }
        if (product.getCategory() == null) {
            throw new BadRequestException("Category of this product cannot be empty!");
        }
        
        categoryRepository.findById(product.getCategory().getId())
            .orElseThrow(() -> new BadRequestException("Category with id: " + product.getCategory().getId() + " doesn't exist!"));

        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        return productRepository.save(product);
    }

    public Product update(String id, Product product) {
        Product prod = productRepository.findById(id)
                            .orElseThrow(() -> new BadRequestException("Product with id: " + id + " doesn't exist!"));

        if (!StringUtils.hasText(product.getName())) {
            throw new BadRequestException("Product name cannot be blank!");
        }
        if (productRepository.findByName(product.getName()) != null && !prod.getName().equalsIgnoreCase(product.getName())) {
            throw new BadRequestException("Product with name: " + product.getName() + " is already exist!");
        }
        prod.setName(product.getName());
        
        if (product.getPrice() != null && product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Price can be smaller than 0 or equal to 0!");
        } else if (product.getPrice() != null) {
            prod.setPrice(product.getPrice());
        }
        

        if (product.getAmount() != 0 && product.getAmount() < 0) {
            throw new BadRequestException("Amount cannot be less than 0");
        } else if (product.getAmount() != 0) {
            prod.setAmount(product.getAmount());
        }

        if (product.getDescription() != null) {
            prod.setDescription(product.getDescription());
        }

        if (product.getCategory() != null) {
            categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new BadRequestException("Category with id: " + product.getCategory().getId() + " doesn't exist!"));

            prod.setCategory(product.getCategory());
        }

        prod.setUpdatedAt(LocalDateTime.now());

        return productRepository.save(prod);
    }

    public void delete(String id) {
        productRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("Product with id: " + id + " doesn't exist!"));

        productRepository.deleteById(id);
    }
}
