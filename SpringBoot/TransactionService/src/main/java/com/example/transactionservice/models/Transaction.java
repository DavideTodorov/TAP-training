package com.example.transactionservice.models;

import java.time.LocalDateTime;
import java.util.UUID;


public class Transaction {

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
