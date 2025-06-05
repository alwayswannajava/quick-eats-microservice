package com.userservice.service.impl;

import com.userservice.dto.request.UpdateUserRequestDto;
import com.userservice.service.exception.UserNotFoundException;
import com.userservice.service.mapper.UserMapper;
import com.userservice.domain.User;
import com.userservice.dto.request.CreateUserRequestDto;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;
import jakarta.persistence.PersistenceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public void create(User user) {
        log.info("Creating a new user: {}", user);
        try {
            userRepository.save(user);
            log.info("User created successfully with ID: {}", user.getUserId());
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage());
            throw new PersistenceException("Error creating user: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User fetchUser(UUID userId) {
        log.info("Fetching user with ID: {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with ID: " + userId));
    }

    @Override
    @Transactional
    public User update(UUID userId, User user) {
        log.info("Updating user with ID: {}", userId);
        try {
            User updatedUser = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
            log.info("Existing user before update: {}", updatedUser);
            userRepository.save(user);
            log.info("User updated successfully: {}", updatedUser);
            return updatedUser;
        } catch (Exception e) {
            log.error("Error updating user: {}", e.getMessage());
            throw new PersistenceException("Error updating user: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(UUID userId) {
        log.info("Deleting user with ID: {}", userId);
        userRepository.deleteById(userId);
    }
}
