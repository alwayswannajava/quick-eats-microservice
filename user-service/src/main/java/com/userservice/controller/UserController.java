package com.userservice.controller;

import com.userservice.dto.request.CreateUserRequestDto;
import com.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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
}
