package com.bboroccu.jwtsample.security.auth.jwt.extractor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

@Component
public class JwtHeaderTokenExtractor implements TokenExtractor {
    //public static String HEADER_PREFIX = "Bearer";
    public static String HEADER_PREFIX = "";

    @Override
    public String extract(String payload) {
        if (StringUtils.isBlank(payload))
            throw new AuthenticationServiceException("Authorization header cannot be blank.");
        if (payload.length() < HEADER_PREFIX.length())
            throw new AuthenticationServiceException("Invalid authorization header size.");
        return payload.substring(HEADER_PREFIX.length(), payload.length());
    }
}

