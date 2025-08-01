package com.userservice.controller;

import com.userservice.dto.response.client.UserClientResponse;
import com.userservice.service.UserClientService;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class UserClientController {
    private final UserClientService userClientService;

    @GetMapping("/fetchClientDetails")
    public ResponseEntity<UserClientResponse> fetchUserClientDetails(
                                                                   @RequestParam @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$",
                                                                           message = "Invalid phone number format")
                                                                   String mobileNumber) {

        UserClientResponse userClientResponse = userClientService.fetchUserClientDetails(mobileNumber);
        return ResponseEntity.ok(userClientResponse);
    }
}
