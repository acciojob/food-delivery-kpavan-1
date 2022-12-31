package com.driver.service.impl;

import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) throws Exception {
        UserEntity userEntity= UserEntity.builder().id(user.getId()).email(user.getEmail()).firstName(user.getFirstName()).lastName(user.getLastName()).userId(user.getUserId()).build();
        userEntity=userRepository.save(userEntity);
        return UserDto.builder().userId(userEntity.getUserId()).firstName(userEntity.getFirstName()).lastName(userEntity.getLastName()).email(userEntity.getEmail()).id(userEntity.getId()).build();
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity userEntity=userRepository.findByEmail(email);
        return UserDto.builder().userId(userEntity.getUserId()).firstName(userEntity.getFirstName()).lastName(userEntity.getLastName()).email(userEntity.getEmail()).id(userEntity.getId()).build();
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity userEntity=userRepository.findByUserId(userId);
        return UserDto.builder().userId(userEntity.getUserId()).firstName(userEntity.getFirstName()).lastName(userEntity.getLastName()).email(userEntity.getEmail()).id(userEntity.getId()).build();
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        UserEntity userEntity=userRepository.findByUserId(userId);
        UserDto userDto=UserDto.builder().userId(userEntity.getUserId()).firstName(userEntity.getFirstName()).lastName(userEntity.getLastName()).email(userEntity.getEmail()).id(userEntity.getId()).build();
        userDto.setUserId(user.getUserId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setId(user.getId());
        userRepository.save(UserEntity.builder().id(user.getId()).email(user.getEmail()).firstName(user.getFirstName()).lastName(user.getLastName()).userId(user.getUserId()).build());
        return userDto;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        userRepository.delete(userRepository.findByUserId(userId));
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> userEntities= (List<UserEntity>) userRepository.findAll();
        List<UserDto> userDtos = null;
        for(UserEntity userEntity:userEntities){
            userDtos.add(UserDto.builder().userId(userEntity.getUserId()).firstName(userEntity.getFirstName()).lastName(userEntity.getLastName()).email(userEntity.getEmail()).id(userEntity.getId()).build());
        }
        return userDtos;
    }
}