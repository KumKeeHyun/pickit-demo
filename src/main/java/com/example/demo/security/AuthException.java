package com.example.demo.security;

import javax.security.sasl.AuthenticationException;

public class AuthException extends AuthenticationException {

    public AuthException(String msg) {
        super(msg);
    }
}
