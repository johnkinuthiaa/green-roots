package com.slippery.greenroots.config;

import com.slippery.greenroots.service.JwtService;
import com.slippery.greenroots.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@Slf4j
public class JwtFilter extends OncePerRequestFilter{
    private final JwtService jwtService;
    private final ApplicationContext applicationContext;

    public JwtFilter(JwtService jwtService, ApplicationContext applicationContext) {
        this.jwtService = jwtService;
        this.applicationContext = applicationContext;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        String authHeader =request.getHeader("Authorization");
        String username =null;
        String token =null;
        if(authHeader !=null &&authHeader.startsWith("Bearer")){
            token =authHeader.substring(7);
            username = jwtService.extractUsernameFromToken(token);
        }else{
            log.warn("expired jwt token");
        }
        if(username !=null &&SecurityContextHolder.getContext().getAuthentication() ==null){
            UserDetails userDetails =applicationContext.getBean(MyUserDetailsService.class).loadUserByUsername(username);
            if(jwtService.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }else{
                log.warn("jwt token is expired");
            }
        }else{
            log.warn("expired jwt token");
        }
        try{
            filterChain.doFilter(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }


    }
}