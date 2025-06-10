package com.userservice.db.migration;

import com.userservice.domain.User;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.util.Arrays;
import java.util.List;

@ChangeUnit(id = "insert-users-data", order = "2", author = "Mykhailo Kornukh")
public class V002__insert_users_table_data {
    @Execution
    public void insertTestData(MongoTemplate mongoTemplate) {
        List<User> testUsers = Arrays.asList(
                User.builder()
                        .email("super.admin@example.com")
                        .phone("+380501234567")
                        .passwordHash("$2b$10$rQZ8kJZXvz1YvZ4VqKjYbeq3TpGfJvA6HjzgHsKaG3L8rG5JvA8Dy")
                        .firstName("Super")
                        .lastName("Admin")
                        .role(User.Role.ADMIN)
                        .status(User.UserStatus.ACTIVE)
                        .emailVerified(true)
                        .phoneVerified(true)
                        .build()
        );
        mongoTemplate.insertAll(testUsers);
    }

    @RollbackExecution
    public void rollbackTestData(MongoTemplate mongoTemplate) {
        mongoTemplate.remove(Query.query(Criteria.where("email")
                .is("super.admin@example.com")), User.class);
    }
}