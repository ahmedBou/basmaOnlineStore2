package com.springboot.rest.api.service;

import com.springboot.rest.api.common.Utils;
import com.springboot.rest.api.dto.UserDto;
import com.springboot.rest.api.entity.RoleEntity;
import com.springboot.rest.api.entity.UserEntity;
import com.springboot.rest.api.exception.UserException;
import com.springboot.rest.api.repository.RoleRepository;
import com.springboot.rest.api.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private Utils utils;

    RoleEntity roleEntity = new RoleEntity();
    List<RoleEntity> roleEntities = new ArrayList<>();



    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity checkUserEmail = userRepository.findByEmail(userDto.getEmail());

        if (checkUserEmail != null) throw new UserException("Email is already exists !");

        ModelMapper modelMapper = new ModelMapper();

        UserEntity userEntity =  modelMapper.map(userDto, UserEntity.class);

        userEntity.setEncryptedPassword(passwordEncoder.encode(userDto.getPassword()));

        // Search for a role
        RoleEntity roleEntity = roleRepository.findByRoleName("admin");

        // if roleEntity not found
        if (roleEntity == null) throw new UserException("Sorry role not found");

        // set role if found
        userEntity.setRoles(roleEntities);

        // Save data to database
        UserEntity userEntity1 = userRepository.save(userEntity);

        UserDto userDto1 = modelMapper.map(userEntity1, UserDto.class);

        return userDto1;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) throw new UsernameNotFoundException("Email not found !");
        List<RoleEntity> roleEntity1 = roleRepository.findByUsers(userEntity);
        UserDetails user = User.withUsername(
                userEntity.getEmail())
                .password(userEntity.getEncryptedPassword())
                .roles(roleEntity1.get(0).getRoleName())
                .build();

        return user;

//        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }


    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) throw new UsernameNotFoundException("Email: " + email + " not found !");

        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userEntity, userDto);

        return userDto;
    }

    @Override
    public UserDto getUserByUuid(String id) {
        // Search for a user
        UserEntity userEntity = userRepository.findById(id);

        // If user not found
        if (userEntity == null) throw new UsernameNotFoundException("User with uuid: " + id + " not found !");

        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userEntity, userDto);

        return userDto;
    }

    @Override
    public UserDto updateUser(String id, UserDto userDto) {
        UserEntity userEntity = userRepository.findById(id);

        if (userEntity == null) throw new UsernameNotFoundException("User with uuid: " + id + " not found !");

        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());

        UserEntity userUpdated = userRepository.save(userEntity);

        UserDto userDto1 = new UserDto();

        BeanUtils.copyProperties(userUpdated, userDto1);

        return userDto1;
    }

    @Override
    public void deleteUser(String id) {
        UserEntity userEntity = userRepository.findById(id);

        if (userEntity == null) throw new UsernameNotFoundException("User with Id: " + id + " not found !");

        userRepository.delete(userEntity);

    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        if (page > 0) page -= 1;

        List<UserDto> usersDto = new ArrayList<>();

        Pageable pageable = PageRequest.of(page, limit);

        Page<UserEntity> userPage = userRepository.findAll(pageable);

        List<UserEntity> users = userPage.getContent();

        for (UserEntity userEntity : users) {
            UserDto user = new UserDto();
            BeanUtils.copyProperties(userEntity, user);

            usersDto.add(user);
        }

        return usersDto;
    }
}
