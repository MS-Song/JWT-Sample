package com.bboroccu.jwtsample.model.dao;

import com.bboroccu.jwtsample.model.entity.Role;
import com.bboroccu.jwtsample.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    public Optional<User> findByUserId(String userId);
    public Optional<User> findByInitToken(String token);
    public Optional<User> findAllByRole(Role role);
}
