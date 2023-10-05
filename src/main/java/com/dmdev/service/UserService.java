package com.dmdev.service;

import com.dmdev.dao.UserRepository;
import com.dmdev.dto.UserReadDto;
import com.dmdev.entity.onetomany.User;
import com.dmdev.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;


    public Optional<UserReadDto> findById(Long id){
        return userRepository.findById(id)
                .map(userReadMapper::mapFrom);
    }

    public boolean delete(Long id){
        Optional<User> maybeUser = userRepository.findById(id);
        maybeUser.ifPresent(user -> userRepository.delete(user.getId()));
        return maybeUser.isPresent();
    }
}
