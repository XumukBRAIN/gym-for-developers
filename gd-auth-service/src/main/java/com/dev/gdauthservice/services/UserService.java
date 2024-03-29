package com.dev.gdauthservice.services;

import com.dev.gdauthservice.dto.UserDto;
import com.dev.gdauthservice.models.User;

public interface UserService {

    User createUser(UserDto userDto);
}
