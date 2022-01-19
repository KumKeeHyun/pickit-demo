package com.example.demo.config;

import com.example.demo.domain.user.entity.User;
import com.example.demo.security.AuthToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaConfig {

    @Bean
    public AuditorAware<User> auditorProvider() {
        return new PickitAuditorAware();
    }

    public static class PickitAuditorAware implements AuditorAware<User> {

        @Override
        public Optional<User> getCurrentAuditor() {
            AuthToken authToken = (AuthToken) SecurityContextHolder.getContext().getAuthentication();
            if (authToken == null || !authToken.isAuthenticated())
                return Optional.empty();

            return Optional.of(authToken)
                    .map(token -> (User)token.getPrincipal());
        }
    }
}
