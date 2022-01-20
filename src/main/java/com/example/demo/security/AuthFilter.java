package com.example.demo.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthFilter extends AbstractAuthenticationProcessingFilter {

    public static final String USER_KEY = "userId";

    public AuthFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String userId = request.getParameter(USER_KEY);
        try {
            Long.parseLong(userId);
        } catch (NumberFormatException e) {
            throw new AuthException("cannot parse userId: " + e.getMessage());
        }

        return getAuthenticationManager().authenticate(new AuthToken(Long.parseLong(userId)));
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        AuthToken authToken = (AuthToken) SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = Optional.ofNullable(authToken)
                .filter(AbstractAuthenticationToken::isAuthenticated)
                .isPresent();
        return !isAuthenticated && super.requiresAuthentication(request, response);
    }

}
