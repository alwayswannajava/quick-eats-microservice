package com.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class User extends BaseEntity {

    @Id
    private String userId;

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String phone;

    @Field(name = "password_hash")
    private String passwordHash;

    @Field(name = "first_name")
    private String firstName;

    @Field(name = "last_name")
    private String lastName;

    @Field(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Field(name = "profile_image")
    private String profileImage;

    private Role role;

    private UserStatus status;

    @Field(name = "email_verified")
    private Boolean emailVerified;

    @Field(name = "phone_verified")
    private Boolean phoneVerified;

    @Field(name = "last_login")
    private LocalDateTime lastLogin;

    public enum Role {
        CUSTOMER,
        RESTAURANT_OWNER,
        COURIER,
        ADMIN
    }

    public enum UserStatus {
        ACTIVE,
        INACTIVE,
        BANNED
    }
}
