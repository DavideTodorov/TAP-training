package com.example.userservice.controllers;

import com.example.userservice.models.User;
import com.example.userservice.models.UserDTO;
import com.example.userservice.services.UserService;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;
    private final Gson gson;

    public UserController(UserService userService, Gson gson) {
        this.userService = userService;
        this.gson = gson;
    }

    //http://localhost:8081/user/create?firstName=Gosho&lastName=Goshov
    @PostMapping("/user/create")
    public String createUser(@RequestParam String firstName,
                             @RequestParam String lastName) {

        UserDTO userDTO = new UserDTO(firstName, lastName);
        String user = userService.createUser(userDTO);

        return user;
    }

    //http://localhost:8081/user/{firstName}
    @GetMapping("/user/{firstName}")
    public String getUser(@PathVariable String firstName) {

        return userService.getUser(firstName);
    }
}
