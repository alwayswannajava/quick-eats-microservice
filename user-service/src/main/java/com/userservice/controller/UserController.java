package com.userservice.controller;

import com.userservice.controller.mapper.UserMapper;
import com.userservice.dto.request.CreateUserRequestDto;
import com.userservice.dto.request.UpdateUserRequestDto;
import com.userservice.dto.response.FetchUserResponseDto;
import com.userservice.dto.response.UpdateUserResponseDto;
import com.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Validated
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/new")
    public ResponseEntity<Void> createUser(@RequestBody @Valid CreateUserRequestDto createUserRequestDto) {
        log.info("------------------------POST REQUEST------------------------");
        log.info("Creating a new user: {}", createUserRequestDto);
        userService.create(userMapper.toUser(createUserRequestDto));
        log.info("------------------------POST REQUEST END------------------------");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UpdateUserResponseDto> updateUser(@PathVariable String userId,
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
    public ResponseEntity<FetchUserResponseDto> fetchUser(@PathVariable String userId) {
        log.info("------------------------GET REQUEST------------------------");
        log.info("Fetching user with ID: {}", userId);
        return ResponseEntity.ok(userMapper.toFetchUserResponseDto(userService.fetchUser(userId)));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        log.info("------------------------DELETE REQUEST------------------------");
        log.info("Deleting user with ID: {}", userId);
        userService.delete(userId);
        log.info("User with ID: {} deleted successfully", userId);
        return ResponseEntity.noContent().build();
    }
}
