package com.example.userservice.services.impl;

import com.example.userservice.models.entities.Address;
import com.example.userservice.models.entities.Transaction;
import com.example.userservice.models.entities.User;
import com.example.userservice.models.entities.UserDTO;
import com.example.userservice.models.repositories.AddressRepository;
import com.example.userservice.models.repositories.UserRepository;
import com.example.userservice.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final HashMap<String, User> userMap;
    private final Gson gson;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public UserServiceImpl(Gson gson, ObjectMapper objectMapper, UserRepository userRepository, AddressRepository addressRepository) {
        this.gson = gson;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.userMap = new HashMap<>();
    }

    @Override
    public String createUser(UserDTO userDTO) {
        if (checkIfUserExists(userDTO.getFirstName())) {
            return "Username with the same firstName already exists.";
        }

        User user = new User(userDTO.getFirstName(), userDTO.getLastName());

        Address addressFromJson = getAddressForUser(user);

        addressRepository.save(addressFromJson);

        user.getAddresses().add(addressFromJson);
        userMap.put(user.getFirstName(), user);

        String userJson = "";
        try {
            userJson = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        userRepository.save(user);

        return userJson;
    }


    @Override
    public String getUser(String firstName, int transactionsCount) {
        if (!checkIfUserExists(firstName)) {
            return "User does not exist!";
        }

        User user = userMap.get(firstName);

        String addressServiceUrl = String.format("http://localhost:8082/address/%s", user.getId());
        RestTemplate restTemplate = new RestTemplate();
        String addresses = restTemplate.getForObject(addressServiceUrl, String.class);

        Address[] allAddresses = gson.fromJson(addresses, Address[].class);

        user.getAddresses().clear();
        user.getAddresses().addAll(Arrays.asList(allAddresses));

        String transactionServiceUrl = String.format("http://localhost:8083/transaction/all/%s", user.getId());
        String transactions = restTemplate.getForObject(transactionServiceUrl, String.class);

        Transaction transaction = null;
        try {
            transaction = objectMapper.readValue(transactions, Transaction.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(transaction);

        String resultJson = "";
        try {
            resultJson = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return resultJson;


    }

    @Override
    public String createAddressForUser(String firstName) {
        if (!checkIfUserExists(firstName)) {
            return "User does not exist!";
        }

        User user = userMap.get(firstName);
        Address addressForUser = getAddressForUser(user);

        return gson.toJson(addressForUser);
    }

    @Override
    public String createTransaction(String firstName) {
        if (!checkIfUserExists(firstName)) {
            return "User does not exist!";
        }

        User user = userMap.get(firstName);
        String addressServiceUrl = String.format("http://localhost:8083/transaction/new?userId=%s", user.getId());

        RestTemplate template = new RestTemplate();
        String result = template.postForObject(addressServiceUrl, null, String.class);

        Transaction transaction = null;
        try {
            transaction = objectMapper.readValue(result, Transaction.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        user.getTransactions().add(transaction);

        return result;
    }

    @Override
    public String updateUser(String firstName, String newFirstName, String newLastName) {
        if (!checkIfUserExists(firstName)) {
            return "User does not exist!";
        }

        User user = userMap.remove(firstName);
        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
        userMap.put(user.getFirstName(), user);

        return gson.toJson(user);
    }

    @Override
    public String deleteUser(String firstName) {
        if (!checkIfUserExists(firstName)) {
            return "User does not exist!";
        }

        userMap.remove(firstName);

        return "User was deleted.";
    }

    private Address getAddressForUser(User user) {
        String addressServiceUrl = String.format("http://localhost:8082/address/create?userId=%s", user.getId());

        RestTemplate template = new RestTemplate();
        String result = template.postForObject(addressServiceUrl, null, String.class);

        return gson.fromJson(result, Address.class);
    }

    private boolean checkIfUserExists(String firstName) {
        return userMap.containsKey(firstName);
    }
}



