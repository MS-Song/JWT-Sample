package com.bboroccu.jwtsample.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.bboroccu.jwtsample.security.exceptions.ExceptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper mapper;

    @Autowired
    public RestAuthenticationEntryPoint(ObjectMapper mapper) {
        this.mapper = mapper;
    }
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ExceptionResponse.CommonExecptionResponse(this.mapper, httpServletResponse, e);
    }
}
