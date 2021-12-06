package com.example.userservice.services.impl;

import com.example.userservice.models.Address;
import com.example.userservice.models.User;
import com.example.userservice.models.UserDTO;
import com.example.userservice.services.UserService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {
    private final HashMap<Long, User> userMap;
    private final Gson gson;

    public UserServiceImpl(Gson gson) {
        this.gson = gson;
        this.userMap = new HashMap<>();
    }

    @Override
    public String createUser(UserDTO userDTO) {
        if (userMap.containsKey(userDTO.getId())) {
            return "User already exists!";
        }
        String addressServiceUrl = String.format("http://localhost:8082/address/create?userId=%d", userDTO.getId());
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(addressServiceUrl, String.class);

        Address addressFromJson = gson.fromJson(result, Address.class);

        User user = new User(userDTO.getId(), userDTO.getFirstName(), userDTO.getLastName(), addressFromJson);
        userMap.put(user.getId(), user);
        return gson.toJson(user);
    }

    @Override
    public String getUser(Long id) {
        if (!userMap.containsKey(id)) {
            return "User does not exist!";
        }

        return gson.toJson(userMap.get(id));
    }
}
