package com.example.userservice.models.repositories;

import com.example.userservice.models.entities.Transaction;
import com.example.userservice.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {

    Optional<User> getUserByFirstName(String firstName);

    void deleteByFirstName(String firstName);


    @Query("{'$expr':{$gte:[{$size:'$transactions'},?0]}}")
    List<User> findAllWithTransactionsMoreThan(int count);

    @Query("{firstName: {$regex: '?0[a-z]+'}}")
    List<User> findAllWithNameStartingWithLetter(String letter);
}
