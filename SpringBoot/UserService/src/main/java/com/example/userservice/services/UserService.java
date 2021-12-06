package com.example.userservice.services;

import com.example.userservice.models.User;
import com.example.userservice.models.UserDTO;

import java.util.UUID;

public interface UserService {

    String createUser(UserDTO userDTO);

    String getUser(String firstName);

    String createAddressForUser(String firstName);

    String createTransaction(String firstName);
}
