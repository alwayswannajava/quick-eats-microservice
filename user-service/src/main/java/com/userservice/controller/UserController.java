package com.userservice.controller;

import com.userservice.dto.request.CreateUserRequestDto;
import com.userservice.dto.request.UpdateUserRequestDto;
import com.userservice.dto.response.UpdateUserResponseDto;
import com.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserRequestDto createUserRequestDto) {
        log.info("------------------------POST REQUEST------------------------");
        log.info("Controller level. Creating a new user: {}", createUserRequestDto);
        userService.create(createUserRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/me")
    public ResponseEntity<UpdateUserResponseDto> updateUser(@RequestBody
                                                                @Valid UpdateUserRequestDto
                                                                        updateUserRequestDto) {
        log.info("------------------------POST REQUEST------------------------");
        log.info("Controller level. Updating user: {}", updateUserRequestDto);
        return null;
    }

    @GetMapping("/fetch/{userId}")
    public ResponseEntity<UpdateUserResponseDto> fetchUser(@PathVariable String userId) {
        log.info("------------------------GET REQUEST------------------------");
        log.info("Controller level. Fetching user with ID: {}", userId);
        return null;
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        log.info("------------------------DELETE REQUEST------------------------");
        log.info("Controller level. Deleting user with ID: {}", userId);
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
