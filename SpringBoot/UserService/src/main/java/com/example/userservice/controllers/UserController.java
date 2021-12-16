package com.example.userservice.controllers;

import com.example.userservice.models.entities.User;
import com.example.userservice.models.entities.UserRequestDTO;
import com.example.userservice.models.entities.UserResponseDTO;
import com.example.userservice.services.UserService;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;
    private final Gson gson;

    public UserController(UserService userService, Gson gson) {
        this.userService = userService;
        this.gson = gson;
    }


    @PostMapping("/user/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequestDTO userData) {
        User user = null;

        try {
            user = userService.createUser(userData);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(user);
    }


    @GetMapping("/user/{firstName}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String firstName) {
        UserResponseDTO userResponseDTO = null;

        try {
            userResponseDTO = userService.getUser(firstName);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(userResponseDTO);
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
        UserRequestDTO userDTO = gson.fromJson(userUpdateDetails, UserRequestDTO.class);

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
