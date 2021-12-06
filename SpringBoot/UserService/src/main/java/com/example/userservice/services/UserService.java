package com.example.userservice.services;

import com.example.userservice.models.User;
import com.example.userservice.models.UserDTO;

public interface UserService {

    String createUser(UserDTO userDTO);

    String getUser(Long id);
}
