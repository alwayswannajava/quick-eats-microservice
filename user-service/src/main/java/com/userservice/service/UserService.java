package com.userservice.service;

import com.userservice.domain.User;

public interface UserService {
    void create(User user);

    User fetchUser(String userId);

    User update(String userId, User user);

    void delete(String userId);
}
