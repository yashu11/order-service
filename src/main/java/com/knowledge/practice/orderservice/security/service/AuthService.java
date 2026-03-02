package com.knowledge.practice.orderservice.security.service;

import com.knowledge.practice.orderservice.security.dto.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest registerRequest);
    String login(String username, String password);
}
