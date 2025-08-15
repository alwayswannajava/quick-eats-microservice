package com.userservice.migration;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.schema.JsonSchemaProperty;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;
import org.springframework.data.mongodb.core.validation.Validator;

@ChangeUnit(id = "V001__create_users_table", order = "1", author = "Mykhailo Kornukh")
public class V001__create_users_table {

    @Execution
    public void createUsersCollection(MongoTemplate mongoTemplate) {
        MongoJsonSchema schema = MongoJsonSchema.builder()
                .required("email", "phone", "password_hash", "first_name", "last_name", "role", "status")
                .properties(
                        JsonSchemaProperty.string("email"),
                        JsonSchemaProperty.string("phone").minLength(10).maxLength(20),
                        JsonSchemaProperty.string("password_hash").minLength(8),
                        JsonSchemaProperty.string("first_name").minLength(2).maxLength(50),
                        JsonSchemaProperty.string("last_name").minLength(2).maxLength(50),
                        JsonSchemaProperty.string("role").possibleValues("CUSTOMER", "RESTAURANT_OWNER", "COURIER", "ADMIN"),
                        JsonSchemaProperty.string("status").possibleValues("ACTIVE", "INACTIVE", "BANNED"),
                        JsonSchemaProperty.bool("email_verified"),
                        JsonSchemaProperty.bool("phone_verified"),
                        JsonSchemaProperty.date("created_at"),
                        JsonSchemaProperty.string("created_by"),
                        JsonSchemaProperty.date("updated_at"),
                        JsonSchemaProperty.string("updated_by"),
                        JsonSchemaProperty.date("last_login")
                ).build();

        mongoTemplate.createCollection("users",
                CollectionOptions.empty()
                        .validator(Validator.schema(schema)));

        IndexOperations indexOps = mongoTemplate.indexOps("users");
        indexOps.createIndex(new Index().on("email",
                Sort.Direction.ASC).unique().named("idx_users_email"));
        indexOps.createIndex(new Index().on("phone",
                Sort.Direction.ASC).unique().named("idx_users_phone"));
    }

    @RollbackExecution
    public void rollbackUsersCollection(MongoTemplate mongoTemplate) {
        mongoTemplate.dropCollection("users");
    }
}