package com.slippery.greenroots.service.impl;

import com.slippery.greenroots.dto.UserDto;
import com.slippery.greenroots.models.Users;
import com.slippery.greenroots.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements com.slippery.greenroots.service.UserService {
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder =new BCryptPasswordEncoder(12);

    public UserService(UserRepository repository, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDto registerUser(Users users) {
        UserDto response =new UserDto();
        var user =repository.findByUsername(users.getUsername());
        var email =repository.findByEmail(users.getEmail());
        if(user !=null){
            response.setMessage("User already exists! ");
            response.setStatusCode(401);
            return response;
        }
        if(email !=null){
            response.setMessage("User with the email already exists! ");
            response.setStatusCode(401);
            return response;
        }


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
