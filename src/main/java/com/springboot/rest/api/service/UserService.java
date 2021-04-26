package com.springboot.rest.api.service;

import com.springboot.rest.api.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);

    UserDto getUser(String email);

    UserDto getUserByUuid(String id);

    UserDto updateUser(String id, UserDto userDto);

    void deleteUser(String id);

    List<UserDto> getUsers(int page, int limit);
}
