package com.example.userservice.controllers;

import com.example.userservice.models.User;
import com.example.userservice.models.UserDTO;
import com.example.userservice.services.UserService;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;
    private final Gson gson;

    public UserController(UserService userService, Gson gson) {
        this.userService = userService;
        this.gson = gson;
    }

    //http://localhost:8081/user/create?id=1&firstName=Gosho&lastName=Goshov
    @GetMapping("/user/create")
    public String createUser(@RequestParam Long id, @RequestParam String firstName,
                             @RequestParam String lastName) {

        UserDTO userDTO = new UserDTO(id, firstName, lastName);
        String userJson = userService.createUser(userDTO);

        return userJson;
    }

    //http://localhost:8081/user/get?id=1
    @GetMapping("/user/get")
    public String getUser(@RequestParam Long id) {
        String userJson = userService.getUser(id);

        return userJson;
    }
}
