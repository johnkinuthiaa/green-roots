package com.slippery.greenroots.controller;

import com.slippery.greenroots.dto.UserDto;
import com.slippery.greenroots.models.Users;
import com.slippery.greenroots.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController{
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    public UserDto registerUser(Users users) {
        return null;
    }

    public UserDto loginUser(Users users) {
        return null;
    }

    public UserDto findUserById(UUID userId) {
        return null;
    }

    public UserDto deleteUserById(UUID userId) {
        return null;
    }

    public UserDto findAllUsers() {
        return null;
    }
}
