package com.bboroccu.jwtsample.service.impl;

import com.bboroccu.jwtsample.model.dao.UserRepo;
import com.bboroccu.jwtsample.model.entity.Role;
import com.bboroccu.jwtsample.model.entity.User;
import com.bboroccu.jwtsample.security.auth.jwt.JwtAuthenticationProvider;
import com.bboroccu.jwtsample.service.CommonService;
import com.bboroccu.jwtsample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends CommonService implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    public Optional<User> getByUserToken(String token) {
        try {
            String userId = jwtAuthenticationProvider.parseUserFromToken(token);
            return getByUserId(userId);
        } catch (Exception ex) {
            logger.error("getByUserToken", ex);
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getByUserId(String userId) {
        return this.userRepo.findByUserId(userId);
    }

    @Override
    public Optional<User> getByToken(String token) {
        return this.userRepo.findByInitToken(token);
    }

    @Override
    public Optional<User> getAllByUser(Role role) {
        return userRepo.findAllByRole(role);
    }

    @Override
    public boolean saveUser(User user) {
        try {
            userRepo.save(user);
            return true;
        } catch (Exception ex) {
            logger.error("saveUser", user.getUserId() + " : " + ex);
            return false;
        }
    }
}
