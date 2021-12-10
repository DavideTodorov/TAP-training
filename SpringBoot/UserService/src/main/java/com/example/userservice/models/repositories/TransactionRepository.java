package com.example.userservice.models.repositories;

import com.example.userservice.models.entities.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TransactionRepository extends CrudRepository<Transaction, UUID> {
}
