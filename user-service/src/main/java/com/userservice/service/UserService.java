package com.userservice.service;

import com.userservice.domain.User;
import com.userservice.dto.message.CreateUserMessageDto;

public interface UserService {
    void create(User user);

    User fetchUser(String userId);

    User fetchUserByPhone(String phone);

    User update(String userId, User user);

    void delete(String userId);

    boolean updateCommunicationStatus(CreateUserMessageDto createUserMessageDto);
}
