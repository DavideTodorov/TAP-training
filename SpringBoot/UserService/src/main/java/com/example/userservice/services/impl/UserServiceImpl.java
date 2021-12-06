package com.example.userservice.services.impl;

import com.example.userservice.models.Address;
import com.example.userservice.models.User;
import com.example.userservice.models.UserDTO;
import com.example.userservice.services.UserService;
import com.google.gson.Gson;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final HashMap<String, User> userMap;
    private final Gson gson;

    public UserServiceImpl(Gson gson) {
        this.gson = gson;
        this.userMap = new HashMap<>();
    }

    @Override
    public String createUser(UserDTO userDTO) {
        if (userMap.containsKey(userDTO.getFirstName())) {
            return "Username with the same firstName already exists.";
        }


        User user = new User(userDTO.getFirstName(), userDTO.getLastName());

        Address addressFromJson = getAddressForUser(user);


        user.getAddress().add(addressFromJson);
        userMap.put(user.getFirstName(), user);

        return gson.toJson(user);
    }


    @Override
    public String getUser(String firstName) {
        if (!userMap.containsKey(firstName)) {
            return "User does not exist!";
        }

        User user = userMap.get(firstName);

        String addressServiceUrl = String.format("http://localhost:8082/address/%s", user.getId());
        RestTemplate restTemplate = new RestTemplate();
        String addresses = restTemplate.getForObject(addressServiceUrl, String.class);

        List list = gson.fromJson(addresses, List.class);
        System.out.println();
//                .forEach(x -> {
//            Address address = (Address) x;
//            if (!user.getAddress().contains(address)) {
//                user.getAddress().add(address);
//            }
//        });

        return gson.toJson(user);


    }

    private Address getAddressForUser(User user) {
        String addressServiceUrl = String.format("http://localhost:8082/address/create?userId=%s", user.getId());

        RestTemplate template = new RestTemplate();
        String result = template.postForObject(addressServiceUrl, null, String.class);

        return gson.fromJson(result, Address.class);
    }
}

