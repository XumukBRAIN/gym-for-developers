package com.dev.gdauthservice.services.impl;

import com.dev.gdauthservice.dto.UserDto;
import com.dev.gdauthservice.repositories.UserRepository;
import com.dev.gdauthservice.security.JwtUtil;
import com.dev.gdauthservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    public String generateTokenForAuthUser(UserDto loginUser) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getName(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return userRepository.findByName(loginUser.getName())
                .map(jwtUtil::generateToken)
                .orElseThrow(() -> new BadCredentialsException("User not found"));
    }
}
