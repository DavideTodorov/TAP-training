package com.example.userservice.controllers;

import com.example.userservice.models.entities.UserDTO;
import com.example.userservice.services.UserService;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;
    private final Gson gson;

    public UserController(UserService userService, Gson gson) {
        this.userService = userService;
        this.gson = gson;
    }


    @PostMapping("/user/create")
    public String createUser(@RequestBody String userData) {
        UserDTO userDTO = gson.fromJson(userData, UserDTO.class);

        String user = userService.createUser(userDTO);

        return user;
    }


    @GetMapping("/user/{firstName}")
    public String getUser(@PathVariable String firstName, @RequestParam int transactionsCount) {

        return userService.getUser(firstName, transactionsCount);
    }


    @PostMapping("user/address/create")
    public String createAddress(@RequestParam String firstName) {
        return userService.createAddressForUser(firstName);
    }


    @PostMapping("user/transaction/create")
    public String createTransaction(@RequestParam String firstName) {
        return userService.createTransaction(firstName);
    }


    @PatchMapping("/user/{firstName}")
    public String updateUser(@PathVariable String firstName, @RequestBody String userUpdateDetails) {
        UserDTO userDTO = gson.fromJson(userUpdateDetails, UserDTO.class);

        return userService.updateUser(firstName, userDTO);
    }


    @DeleteMapping("/user/{firstName}")
    public String deleteUser(@PathVariable String firstName) {
        return userService.deleteUser(firstName);
    }


    @GetMapping("/user/movies/all")
    public String getAllMovies() {
        return userService.getAllMovies();
    }


    @PostMapping("user/{userFirstName}/movie/{movieName}")
    public String rentMovie(@PathVariable String userFirstName, @PathVariable String movieName) {
        return userService.rentMovie(movieName, userFirstName);
    }
}
