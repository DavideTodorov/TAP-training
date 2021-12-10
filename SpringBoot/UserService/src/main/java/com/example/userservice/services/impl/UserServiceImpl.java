package com.example.userservice.services.impl;

import com.example.userservice.models.entities.Address;
import com.example.userservice.models.entities.Transaction;
import com.example.userservice.models.entities.User;
import com.example.userservice.models.entities.UserDTO;
import com.example.userservice.models.repositories.AddressRepository;
import com.example.userservice.models.repositories.TransactionRepository;
import com.example.userservice.models.repositories.UserRepository;
import com.example.userservice.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final Gson gson;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final TransactionRepository transactionRepository;

    public UserServiceImpl(Gson gson, ObjectMapper objectMapper, UserRepository userRepository,
                           AddressRepository addressRepository, TransactionRepository transactionRepository) {
        this.gson = gson;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.transactionRepository = transactionRepository;
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

        User user = userRepository.getUserByFirstName(firstName).orElse(null);

        List<Transaction> addressList = user.getTransactions().stream().limit(transactionsCount)
                .collect(Collectors.toList());

        user.setTransactions(addressList);

        String userJson = "";
        try {
            userJson = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return userJson;
    }

    @Override
    public String createAddressForUser(String firstName) {
        if (!checkIfUserExists(firstName)) {
            return "User does not exist!";
        }

        User user = userRepository.getUserByFirstName(firstName).orElse(null);
        Address addressForUser = getAddressForUser(user);
        addressRepository.save(addressForUser);

        user.getAddresses().add(addressForUser);
        userRepository.save(user);

        String userJson = "";
        try {
            userJson = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return userJson;
    }

    @Override
    public String createTransaction(String firstName) {
        if (!checkIfUserExists(firstName)) {
            return "User does not exist!";
        }

        User user = userRepository.getUserByFirstName(firstName).orElse(null);
        String addressServiceUrl = String.format("http://localhost:8083/transaction/new?userId=%s", user.getId());

        RestTemplate template = new RestTemplate();
        String result = template.postForObject(addressServiceUrl, null, String.class);

        Transaction transaction = null;
        try {
            transaction = objectMapper.readValue(result, Transaction.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        transactionRepository.save(transaction);
        user.getTransactions().add(transaction);
        userRepository.save(user);


        String userJson = "";
        try {
            userJson = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return userJson;
    }

    @Override
    public String updateUser(String firstName, String newFirstName, String newLastName) {
        if (!checkIfUserExists(firstName)) {
            return "User does not exist!";
        }

        User user = userRepository.getUserByFirstName(firstName).orElse(null);
        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
        userRepository.save(user);

        String userJson = "";
        try {
            userJson = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return userJson;
    }

    @Override
    public String deleteUser(String firstName) {
        if (!checkIfUserExists(firstName)) {
            return "User does not exist!";
        }

        User user = userRepository.getUserByFirstName(firstName).orElse(null);

        List<Address> addresses = user.getAddresses();
        addressRepository.deleteAll(addresses);

        List<Transaction> transactions = user.getTransactions();
        transactionRepository.deleteAll(transactions);

        userRepository.delete(user);

        return "User was deleted.";
    }

    private Address getAddressForUser(User user) {
        String addressServiceUrl = String.format("http://localhost:8082/address/create?userId=%s", user.getId());

        RestTemplate template = new RestTemplate();
        String result = template.postForObject(addressServiceUrl, null, String.class);

        return gson.fromJson(result, Address.class);
    }

    private boolean checkIfUserExists(String firstName) {
        return userRepository.getUserByFirstName(firstName).isPresent();
    }
}



