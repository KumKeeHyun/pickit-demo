package com.example.demo.security;

import com.example.demo.domain.user.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        User principal = (User) authentication.getPrincipal();
        ObjectMapper objectMapper = new ObjectMapper();

        response.getWriter().println(objectMapper.writeValueAsString(principal));
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
    }
}
