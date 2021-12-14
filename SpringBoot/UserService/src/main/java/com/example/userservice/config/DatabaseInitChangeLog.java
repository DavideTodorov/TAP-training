package com.example.userservice.config;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "client-initializer", order = "1", author = "davide")
public class DatabaseInitChangeLog {
    private final MongoTemplate mongoTemplate;

    public DatabaseInitChangeLog(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void changeSet() {
        System.out.println();
    }

    @RollbackExecution
    public void rollback() {
        System.out.println();
    }
}