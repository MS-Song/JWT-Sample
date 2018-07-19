package com.bboroccu.jwtsample.security.model.token;

import com.bboroccu.jwtsample.security.config.JwtSettings;
import com.bboroccu.jwtsample.security.model.Scopes;
import com.bboroccu.jwtsample.security.model.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtTokenFactory {
    private final JwtSettings settings;

    @Autowired
    public JwtTokenFactory(JwtSettings settings) {
        this.settings = settings;
    }

    public AccessJwtToken createAccessJwtToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUserId()))
            throw new IllegalArgumentException("Cannot create JWT Token without userId");
        if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty())
            throw new IllegalArgumentException("user doesn't have any privileages");

        Claims claims = Jwts.claims().setSubject(userContext.getUserId());
        claims.put("scopes", userContext.getAuthorities().stream().map(s->s.toString()).collect(Collectors.toList()));

        LocalDateTime currentTime = LocalDateTime.now();

        String token = Jwts.builder().setClaims(claims)
                .setIssuer(settings.getTokenIsuser())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime.plusMinutes(settings.getTokenExpirationTime()).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();
        return new AccessJwtToken(token, claims);
    }
    public JwtToken createRefreshToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUserId()))
            throw new IllegalArgumentException("Cannot create JWT Token without userId");
        LocalDateTime currentTime = LocalDateTime.now();
        Claims claims = Jwts.claims().setSubject(userContext.getUserId());
        claims.put("scopes", Arrays.asList(Scopes.REFRESH_TOKEN.authority()));
        String token = Jwts.builder().setClaims(claims)
                .setIssuer(settings.getTokenIsuser())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime.plusMinutes(settings.getRefreshTokenExpTime()).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();
        return new AccessJwtToken(token, claims);
    }
}
