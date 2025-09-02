package com.userservice.service.impl;

import com.mongodb.MongoException;
import com.userservice.dto.message.CreateUserMessageDto;
import com.userservice.service.exception.UserNotFoundException;
import com.userservice.controller.mapper.UserMapper;
import com.userservice.domain.User;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final StreamBridge streamBridge;

    @Override
    public void create(User user) {
        log.info("Creating a new user: {}", user);
        try {
            log.info("Trying to save user with email: {}", user.getEmail());
            var savedUser = userRepository.save(user);
            sendCommunication(savedUser);
            log.info("User created successfully with email: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage());
            throw new MongoException("Error creating user: " + e.getMessage());
        }
    }

    @Override
    public User fetchUser(String userId) {
        log.info("Fetching user with ID: {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with ID: " + userId));
    }

    @Override
    public User fetchUserByPhone(String phone) {
        return userRepository.findByPhone(phone)
                .orElseThrow(() -> new UserNotFoundException("User not found with phone: " + phone));
    }

    @Override
    public User update(String userId, User user) {
        log.info("Updating user with ID: {}", userId);
        try {
            var existingUser = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
            log.info("Existing user before update: {}", existingUser);
            userMapper.toUser(existingUser, user);
            userRepository.save(existingUser);
            log.info("User updated successfully");
            log.info("Updated user details: {}", existingUser);
            return existingUser;
        } catch (Exception e) {
            log.error("Error updating user: {}", e.getMessage());
            throw new MongoException("Error updating user: " + e.getMessage());
        }
    }

    @Override
    public void delete(String userId) {
        log.info("Deleting user with ID: {}", userId);
        userRepository.deleteById(userId);
        log.info("User with ID: {} deleted successfully", userId);
    }

    @Override
    public boolean updateCommunicationStatus(CreateUserMessageDto createUserMessageDto) {
        return true;
    }

    private void sendCommunication(User user) {
        var createUserMessageDto = new CreateUserMessageDto(
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getEmail());
        log.info("Sending communication for the details: {}", createUserMessageDto);
        boolean result = streamBridge.send("sendCommunication-out-0", createUserMessageDto);
        log.info("Is the communication request is successfully processed? : {}", result);
    }
}
