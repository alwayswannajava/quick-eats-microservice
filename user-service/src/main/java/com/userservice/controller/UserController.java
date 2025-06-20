package com.userservice.controller;

import com.userservice.controller.mapper.UserMapper;
import com.userservice.dto.request.CreateUserRequest;
import com.userservice.dto.request.UpdateUserRequest;
import com.userservice.dto.response.FetchUserResponse;
import com.userservice.dto.response.UpdateUserResponse;
import com.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Validated
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/new")
    public ResponseEntity<Void> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        log.info("------------------------POST REQUEST------------------------");
        log.info("Creating a new user: {}", createUserRequest);
        userService.create(userMapper.toUser(createUserRequest));
        log.info("------------------------POST REQUEST END------------------------");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/update/{userId}")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable String userId,
                                                         @RequestBody
                                                            @Valid UpdateUserRequest
                                                                    updateUserRequest) {
        log.info("------------------------POST REQUEST------------------------");
        log.info("Updating user: {}", updateUserRequest);
        return ResponseEntity.ok(
                userMapper.toUpdateUserResponseDto(
                        userService.update(userId, userMapper.toUser(updateUserRequest))));
    }

    @GetMapping("/fetch/{userId}")
    public ResponseEntity<FetchUserResponse> fetchUser(@PathVariable String userId) {
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
