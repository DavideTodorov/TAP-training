package com.example.userservice.models.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Document
public class Transaction {

    @Id
    private UUID id;
    private LocalDateTime dateOfExecution;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDateOfExecution() {
        return dateOfExecution;
    }

    public void setDateOfExecution(LocalDateTime dateOfExecution) {
        this.dateOfExecution = dateOfExecution;
    }
}
