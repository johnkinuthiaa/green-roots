package com.slippery.greenroots.service.impl;

import com.slippery.greenroots.dto.UserDto;
import com.slippery.greenroots.models.Users;
import com.slippery.greenroots.repository.UserRepository;
import com.slippery.greenroots.service.JwtService;
import com.slippery.greenroots.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder =new BCryptPasswordEncoder(12);
    private final JwtService jwtService;

    public UserServiceImplementation(UserRepository repository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setJoinedOn(LocalDateTime.now());
        repository.save(users);
        response.setMessage("User created successfully! ");
        response.setStatusCode(201);
        response.setUser(users);

        return response;
    }

    @Override
    public UserDto loginUser(Users users) {
        UserDto response =new UserDto();
        var email =repository.findByEmail(users.getEmail());
        if(email ==null){
            response.setMessage("User with the email does not exists! ");
            response.setStatusCode(401);
            return response;
        }
        String username =email.getUsername();
        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            username,users.getPassword()
        ));
        if(authentication.isAuthenticated()){
            var token =jwtService.generateJwtToken(username);
            response.setAccessKey(token);
            authentication.setAuthenticated(true);
            response.setMessage("User authenticated successfully !");
            response.setStatusCode(200);
        }else{
            authentication.setAuthenticated(false);
            response.setMessage("User not authenticated");
            response.setStatusCode(401);
        }

        return null;
    }

    @Override
    public UserDto findUserById(UUID userId) {
        UserDto response =new UserDto();
        var user =repository.findById(userId);
        if(user.isEmpty()){
            response.setMessage("User with id "+userId +"does not exist");
            response.setStatusCode(404);
            return response;
        }
        response.setStatusCode(200);
        response.setMessage("User with id "+userId);
        response.setUser(user.get());
        return response;
    }

    @Override
    public UserDto deleteUserById(UUID userId) {
        UserDto response =new UserDto();
        var user =findUserById(userId);
        if(user.getStatusCode() !=200){
            return user;
        }
        repository.delete(user.getUser());
        response.setStatusCode(200);
        response.setMessage("User deleted successfully");
        return response;
    }

    @Override
    public UserDto findAllUsers() {
        UserDto response =new UserDto();
        var users =repository.findAll();
        if(users.isEmpty()){
            response.setMessage("Users list is empty");
            response.setStatusCode(404);
            return response;
        }
        response.setStatusCode(200);
        response.setUsers(users);
        response.setMessage("All users");
        return response;
    }
}
