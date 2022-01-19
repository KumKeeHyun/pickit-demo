package com.example.demo.security;

import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User unauthenticatedUser = (User) authentication.getPrincipal();
        User user = userRepository.findById(unauthenticatedUser.getId())
                .orElseThrow(() -> new AuthException("cannot find user: " + unauthenticatedUser.getId()));

        return new AuthToken(user, Collections.singleton(user.getRole()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AuthToken.class.isAssignableFrom(authentication);
    }
}
