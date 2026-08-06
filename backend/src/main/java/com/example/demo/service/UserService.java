package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;
import com.example.demo.payload.RegisterRequest;

public interface UserService {
    User register(RegisterRequest request);
     User create(User user);
        List<User> getAll();
        User getById(Long id);
        User update(Long id, User user);
        void delete(Long id);
    }



