package com.knowledge.practice.orderservice.security.service;

import com.knowledge.practice.orderservice.security.CustomUserDetailsService;
import com.knowledge.practice.orderservice.security.dto.RegisterRequest;
import com.knowledge.practice.orderservice.user.model.UserDetails;
import com.knowledge.practice.orderservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest registerRequest) {
        UserDetails userDetails =UserDetails.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role("ROLE_USER")
                .build();

        userRepository.save(userDetails);
    }
}
