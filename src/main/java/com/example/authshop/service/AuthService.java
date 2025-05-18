package com.example.authshop.service;

import com.example.authshop.dto.AuthResponse;
import com.example.authshop.dto.LoginRequest;
import com.example.authshop.dto.RegisterRequest;
import com.example.authshop.model.Role;
import com.example.authshop.model.User;
import com.example.authshop.repository.UserRepository;
import com.example.authshop.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtService jwtService;

    public AuthResponse register (RegisterRequest request){
        if(userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("Username already taken");
        }
        User user = new User(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                Collections.singleton(Role.ROLE_USER)
        );
        userRepository.save(user);
        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);
    }
    public AuthResponse login (LoginRequest request){
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);
    }
}
