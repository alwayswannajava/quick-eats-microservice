package com.userservice.controller;

import com.userservice.domain.User;
import com.userservice.service.mapper.UserMapper;
import com.userservice.dto.request.CreateUserRequestDto;
import com.userservice.dto.request.UpdateUserRequestDto;
import com.userservice.dto.response.FetchUserResponseDto;
import com.userservice.dto.response.UpdateUserResponseDto;
import com.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createUser(@RequestBody @Valid CreateUserRequestDto createUserRequestDto) {
        log.info("------------------------POST REQUEST------------------------");
        log.info("Creating a new user: {}", createUserRequestDto);
        userService.create(userMapper.toUser(createUserRequestDto));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UpdateUserResponseDto> updateUser(@PathVariable UUID userId,
                                                            @RequestBody
                                                            @Valid UpdateUserRequestDto
                                                                    updateUserRequestDto) {
        log.info("------------------------POST REQUEST------------------------");
        log.info("Updating user: {}", updateUserRequestDto);
        return ResponseEntity.ok(
                userMapper.toUpdateUserResponseDto(
                        userService.update(userId, userMapper.toUser(updateUserRequestDto))));
    }

    @GetMapping("/fetch/{userId}")
    public ResponseEntity<FetchUserResponseDto> fetchUser(@PathVariable UUID userId) {
        log.info("------------------------GET REQUEST------------------------");
        log.info("Fetching user with ID: {}", userId);
        return ResponseEntity.ok(userMapper.toFetchUserResponseDto(userService.fetchUser(userId)));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        log.info("------------------------DELETE REQUEST------------------------");
        log.info("Deleting user with ID: {}", userId);
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
