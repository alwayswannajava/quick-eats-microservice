package com.userservice.service.impl;

import com.userservice.dto.request.CreateUserRequestDto;
import com.userservice.dto.request.FetchUserRequestDto;
import com.userservice.dto.response.UpdateUserResponseDto;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void create(CreateUserRequestDto createUserRequestDto) {
        log.info("Service level. Creating a new user: {}", createUserRequestDto);
    }

    @Override
    @Transactional(readOnly = true)
    public FetchUserRequestDto fetchUser(String userId) {
        return null;
    }

    @Override
    @Transactional
    public UpdateUserResponseDto update(String userId) {
        return null;
    }

    @Override
    @Transactional
    public void delete(String userId) {

    }
}
