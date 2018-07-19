package com.bboroccu.jwtsample.security.auth.jwt.verifier;

public interface TokenVerifier {
    public boolean verify(String jti);
}
