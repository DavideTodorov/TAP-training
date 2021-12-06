package com.example.transactionservice.controllers;

import com.example.transactionservice.services.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transaction")
    public String createTransaction() {
        return transactionService.createTransaction(UUID.randomUUID());
    }

}
