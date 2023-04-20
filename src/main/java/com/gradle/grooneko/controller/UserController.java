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

import com.gradle.grooneko.entity.User;
import com.gradle.grooneko.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/user/id={id}")
    public User findById(@PathVariable(value = "id") String id) {
        return userService.findById(id);
    }
    @GetMapping("/user/username={username}")
    public User findByUsername(@PathVariable(value = "username") String username) {
        return userService.findByUsername(username);
    }

    @PostMapping("/user")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PatchMapping("/user/{id}")
    public User update(@PathVariable(value = "id") String id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable(value = "id") String id) {
        userService.delete(id);
    } 
}
