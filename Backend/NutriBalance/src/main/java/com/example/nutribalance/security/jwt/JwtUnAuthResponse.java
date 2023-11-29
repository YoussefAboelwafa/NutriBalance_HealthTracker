package com.example.nutribalance.security.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Component
public class JwtUnAuthResponse implements AuthenticationEntryPoint, Serializable {
    @Serial
    private static final long serialVersionUID = 2848589597094595376L;


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        authException.printStackTrace();
        final String expired = (String) request.getAttribute("expired");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Objects.requireNonNullElse(expired, "You would need to provide the Jwt token to access this resource"));
    }
}

