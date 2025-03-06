package com.slippery.greenroots.service;

import com.slippery.greenroots.dto.UserDto;
import com.slippery.greenroots.models.Users;

public interface UserService {
    UserDto registerUser(Users users);
    UserDto loginUser(Users users);
    UserDto findUserById(Long userId);
    UserDto deleteUserById(Long userId);
    UserDto findAllUsers();
}
