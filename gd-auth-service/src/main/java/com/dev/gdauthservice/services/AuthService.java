package com.dev.gdauthservice.services;

import com.dev.gdauthservice.dto.UserDto;
import org.springframework.security.core.AuthenticationException;

public interface AuthService {

    String generateTokenForAuthUser(UserDto loginUser) throws AuthenticationException;
}
