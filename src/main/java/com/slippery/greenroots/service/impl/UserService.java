package com.slippery.greenroots.service.impl;

import com.slippery.greenroots.dto.UserDto;
import com.slippery.greenroots.models.Users;
import com.slippery.greenroots.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements com.slippery.greenroots.service.UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDto registerUser(Users users) {
        return null;
    }

    @Override
    public UserDto loginUser(Users users) {
        return null;
    }

    @Override
    public UserDto findUserById(UUID userId) {
        return null;
    }

    @Override
    public UserDto deleteUserById(UUID userId) {
        return null;
    }

    @Override
    public UserDto findAllUsers() {
        return null;
    }
}
