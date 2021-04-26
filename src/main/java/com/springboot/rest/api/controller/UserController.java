package com.springboot.rest.api.controller;

import com.springboot.rest.api.dto.UserDto;
import com.springboot.rest.api.request.UserRequest;
import com.springboot.rest.api.response.UserResponse;
import com.springboot.rest.api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value ="/users")
public class UserController {
    @Autowired
    private UserService userService;

    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public List<UserResponse> getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "limit", defaultValue = "20") int limit) {

        List<UserDto> users = userService.getUsers(page, limit);

        List<UserResponse> listUsersResponse = new ArrayList<>();

        for (UserDto userDto : users) {
            UserResponse user = modelMapper.map(userDto, UserResponse.class);

            listUsersResponse.add(user);
        }

        return listUsersResponse;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserRequest userRequest) throws Exception {

        UserDto userDto = modelMapper.map(userRequest, UserDto.class);

        userService.createUser(userDto);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") String id, @RequestBody UserRequest userRequest) {

        UserDto userDto = modelMapper.map(userRequest, UserDto.class);

        userService.updateUser(id, userDto);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") String id) {

        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
