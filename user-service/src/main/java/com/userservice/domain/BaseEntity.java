package com.userservice.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public abstract class BaseEntity {

    @CreatedDate
    @Field(name = "created_at")
    protected LocalDateTime createdAt;

    @CreatedBy
    @Field(name = "created_by")
    protected String createdBy;

    @LastModifiedDate
    @Field(name = "updated_at")
    protected LocalDateTime updatedAt;

    @LastModifiedBy
    @Field(name = "updated_by")
    protected String updatedBy;

}
