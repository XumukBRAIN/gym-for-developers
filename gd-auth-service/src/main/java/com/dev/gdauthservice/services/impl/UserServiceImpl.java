package com.dev.gdauthservice.services.impl;

import com.dev.gdauthservice.dto.UserDto;
import com.dev.gdauthservice.models.User;
import com.dev.gdauthservice.repositories.UserRepository;
import com.dev.gdauthservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(UserDto userDto) {
        User newUser = new User();
        newUser.setName(userDto.getName());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(newUser);
    }
}
