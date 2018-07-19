package com.bboroccu.jwtsample.security.auth.jwt.extractor;

public interface TokenExtractor {
    public String extract(String payload);
}
