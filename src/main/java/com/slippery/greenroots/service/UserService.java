package com.slippery.greenroots.service;

import com.slippery.greenroots.dto.UserDto;
import com.slippery.greenroots.models.Users;

import java.util.UUID;

public interface UserService {
    UserDto registerUser(Users users);
    UserDto loginUser(Users users);
    UserDto findUserById(UUID userId);
    UserDto deleteUserById(UUID userId);
    UserDto findAllUsers();
}
