package com.example.demo.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.payload.LoginRequest;
import com.example.demo.payload.LoginResponse;
import com.example.demo.payload.RegisterRequest;
import com.example.demo.security.JwtUtils;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
   @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private  CustomUserDetailsService userDetailsService;
    @Autowired
    private  UserService userService;
    
    @Autowired
    private  JwtUtils jwtUtil;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest req) {
        return userService.register(req);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {

        try {
            Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    req.getEmail(),
                    req.getPassword()
                )
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid email or password");
        }

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(req.getEmail());

        String token = jwtUtil.generateToken(userDetails); // ✅ correct

        String role = userDetails.getAuthorities()
                .iterator()
                .next()
                .getAuthority();

        return new LoginResponse(token, role);
    }


}

