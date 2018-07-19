package com.bboroccu.jwtsample.service;

import com.bboroccu.jwtsample.model.entity.Role;
import com.bboroccu.jwtsample.model.entity.User;

import java.util.Optional;

public interface UserService {
    public Optional<User> getByUserToken(String token);
    public Optional<User> getByUserId(String userId);
    public Optional<User> getByToken(String token);
    public Optional<User> getAllByUser(Role role);
    public boolean saveUser(User user);
}
