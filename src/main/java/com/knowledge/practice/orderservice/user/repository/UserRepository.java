package com.knowledge.practice.orderservice.user.repository;

import com.knowledge.practice.orderservice.user.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {

    Optional<UserDetails> findByUsername(String username);
}
