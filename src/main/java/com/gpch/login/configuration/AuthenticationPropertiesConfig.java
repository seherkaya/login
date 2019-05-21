package com.gpch.login.configuration;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.gpch.login.model.User;
import com.gpch.login.model.Role;
import com.gpch.login.repository.UserRepository;

import java.util.Collections;
import java.util.List;

public class AuthenticationPropertiesConfig implements AuthenticationProvider{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String userName= authentication.getName();
        String password=authentication.getCredentials().toString();
        if ("externaluser".equals(userName) && "pass".equals(password)) {
            return new UsernamePasswordAuthenticationToken
                    (userName, password);
        } else {
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals( UsernamePasswordAuthenticationToken.class );
    }
}
