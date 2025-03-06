package com.slippery.greenroots.service;

import com.slippery.greenroots.models.UserPrincipal;
import com.slippery.greenroots.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    public MyUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user =repository.findByUsername(username);
        if(user ==null){
            throw new UsernameNotFoundException("User was not found");
        }
        return new UserPrincipal(user);
    }
}
