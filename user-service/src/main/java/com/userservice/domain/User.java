package com.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class User {

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

    @Builder.Default
    private UserStatus status = UserStatus.ACTIVE;

    @Field(name = "email_verified")
    private Boolean emailVerified;

    @Field(name = "phone_verified")
    private Boolean phoneVerified;

    @Field(name = "last_login")
    private LocalDateTime lastLogin;

    @CreatedDate
    @Field(name = "created_at")
    private LocalDateTime createdAt;

    @CreatedBy
    @Field(name = "created_by")
    private String createdBy;

    @LastModifiedDate
    @Field(name = "updated_at")
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Field(name = "updated_by")
    private String updatedBy;

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
