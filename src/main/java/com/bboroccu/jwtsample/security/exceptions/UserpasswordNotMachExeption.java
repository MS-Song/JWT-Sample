package com.bboroccu.jwtsample.security.exceptions;

import org.springframework.security.authentication.AuthenticationServiceException;

public class UserpasswordNotMachExeption extends AuthenticationServiceException {
    public UserpasswordNotMachExeption(String msg) {
        super(msg);
    }
}
