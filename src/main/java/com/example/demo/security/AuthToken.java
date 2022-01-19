package com.example.demo.security;

import com.example.demo.domain.user.entity.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthToken extends AbstractAuthenticationToken {

    private final User principal;

    public AuthToken(Long userId) {
        super(null);
        this.setAuthenticated(false);
        this.principal = new User(userId, null, null);
    }

    public AuthToken(User principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.setAuthenticated(true);
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
