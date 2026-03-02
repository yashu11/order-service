package com.knowledge.practice.orderservice.security;

import com.knowledge.practice.orderservice.user.model.UserDetails;
import com.knowledge.practice.orderservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userDetails -> UserDetails.builder()
                        .username(userDetails.getUsername())
                        .password(userDetails.getPassword())
                        .role(userDetails.getRole().replace("ROLE_", ""))
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
