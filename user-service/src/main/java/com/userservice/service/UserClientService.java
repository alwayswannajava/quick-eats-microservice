package com.userservice.service;

import com.userservice.dto.response.client.UserClientResponse;

public interface UserClientService {
    UserClientResponse fetchUserClientDetails(String mobileNumber);
}
