package com.example.transactionservice.services;

import java.util.UUID;

public interface TransactionService {

    String createTransaction(UUID userId);

    String getAll(UUID userId);
}
