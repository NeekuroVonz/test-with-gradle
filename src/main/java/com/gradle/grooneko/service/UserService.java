package com.gradle.grooneko.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gradle.grooneko.entity.User;
import com.gradle.grooneko.exception.BadRequestException;
import com.gradle.grooneko.exception.ResourceNotFoundException;
import com.gradle.grooneko.model.UserRole;
import com.gradle.grooneko.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found!"));
    }

    public User findByUsername(String username) {
        if (userRepository.findByUsername(username) == null) {
            throw new ResourceNotFoundException("User with username: " + username + " not found!");
        }
        return userRepository.findByUsername(username);
    }

    public User create(User user) {
        user.setId(UUID.randomUUID().toString());

        if (!StringUtils.hasText(user.getUsername())) {
            throw new BadRequestException("Username cannot be null!");
        }
        if (userRepository.findByUsername(user.getUsername()) != null && userRepository.findByUsername(user.getUsername()).getUsername().equals(user.getUsername())) {
            throw new BadRequestException("Username: " + user.getUsername() + " already taken!");
        }
        if (!StringUtils.hasText(user.getPassword())) {
            throw new BadRequestException("Password cannot be null");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email: " + user.getEmail() + " already exist!");
        }
        
        user.setRole(UserRole.USER);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User update(String id, User user) {
        return userRepository.save(user);
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }
}
