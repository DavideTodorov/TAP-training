package com.example.userservice.services;

import com.example.userservice.models.entities.*;
import com.example.userservice.models.repositories.AddressRepository;
import com.example.userservice.models.repositories.TransactionRepository;
import com.example.userservice.models.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final Gson gson;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final TransactionRepository transactionRepository;

    public UserService(Gson gson, ObjectMapper objectMapper, UserRepository userRepository,
                       AddressRepository addressRepository, TransactionRepository transactionRepository) {
        this.gson = gson;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.transactionRepository = transactionRepository;
    }

    public User createUser(UserDTO userDTO) {
        if (checkIfUserExists(userDTO.getFirstName())) {
            throw new IllegalStateException("User does not exist");
        }

        User user = new User(userDTO.getFirstName(), userDTO.getLastName());

        String addressServiceUrl = String.format("http://localhost:8082/address/create?userId=%s", user.getId());

        RestTemplate template = new RestTemplate();
        String result = template.postForObject(addressServiceUrl, null, String.class);

        userRepository.save(user);

        return user;
    }


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

    public String updateUser(String firstName, UserDTO userDTO) {
        if (!checkIfUserExists(firstName)) {
            return "User does not exist!";
        }

        User user = userRepository.getUserByFirstName(firstName).orElse(null);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        userRepository.save(user);

        String userJson = "";
        try {
            userJson = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return userJson;
    }

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

    public String rentMovie(String movieName, String userFirstName) {
        if (!checkIfUserExists(userFirstName)) {
            return "User does not exist!";
        }

        String movieURL = String.format("http://localhost:8085/movie/%s", movieName);
        RestTemplate template = new RestTemplate();

        String movieJson = template.getForObject(movieURL, String.class);

        if (!movieJson.contains("id")) {
            return "Movie is not available or does not exist.";
        }

        Movie movie = null;
        try {
            movie = objectMapper.readValue(movieJson, Movie.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        User user = userRepository.getUserByFirstName(userFirstName).orElse(null);

        user.getMovies().add(movie);
        userRepository.save(user);

        String userJson = "";
        try {
            userJson = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return userJson;
    }

    public String getAllMovies() {
        String movieURL = String.format("http://localhost:8085/movie/all");
        RestTemplate template = new RestTemplate();
        String allMoviesJson = template.getForObject(movieURL, String.class);

        return allMoviesJson;
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



