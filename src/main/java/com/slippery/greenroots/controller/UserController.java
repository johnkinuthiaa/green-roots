package com.slippery.greenroots.controller;

import com.slippery.greenroots.dto.UserDto;
import com.slippery.greenroots.models.Users;
import com.slippery.greenroots.service.UserService;
import org.apache.coyote.Response;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController{
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody Users users) {
        return ResponseEntity.ok(service.registerUser(users));
    }
    @GetMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody Users users) {
        return ResponseEntity.ok(service.loginUser(users));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(service.findUserById(userId));
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<UserDto> deleteUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(service.deleteUserById(userId));
    }
    @GetMapping("/all")

    public ResponseEntity<UserDto> findAllUsers() {
        return ResponseEntity.ok(service.findAllUsers());
    }
}
