package com.userservice.repository;

import com.userservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {

}
