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
    public String getUser(@PathVariable String firstName, @RequestParam int transactionsCount) {

        return userService.getUser(firstName, transactionsCount);
    }

    //http://localhost:8081/user/address/create
    @PostMapping("user/address/create")
    public String createAddress(@RequestParam String firstName) {
        return userService.createAddressForUser(firstName);
    }

    @PostMapping("user/transaction/create")
    public String createTransaction(@RequestParam String firstName){
        return userService.createTransaction(firstName);
    }

    @PatchMapping("/user/{firstName}")
    public String updateUser(@PathVariable String firstName, @RequestParam String newFirstName, @RequestParam String newLastName){
        return userService.updateUser(firstName, newFirstName, newLastName);
    }

    @DeleteMapping("/user/{firstName}")
    public String deleteUser(@PathVariable String firstName){
        return userService.deleteUser(firstName);
    }
}
