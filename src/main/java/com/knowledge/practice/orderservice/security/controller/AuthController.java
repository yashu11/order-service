package com.knowledge.practice.orderservice.security.controller;

import com.knowledge.practice.orderservice.security.dto.RegisterRequest;
import com.knowledge.practice.orderservice.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest registerRequest) {
         authService.register(registerRequest);
         return ("User registered successfully");
    }
}
