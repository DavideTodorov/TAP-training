package com.example.userservice.services;

import com.example.userservice.models.entities.UserDTO;

public interface UserService {

    String createUser(UserDTO userDTO);

    String getUser(String firstName, int transactionsCount);

    String createAddressForUser(String firstName);

    String createTransaction(String firstName);

    String updateUser(String firstName, String newFirstName, String newLastName);

    String deleteUser(String firstName);

    String rentMovie(String movieName, String userFirstName);

    String getAllMovies();
}
