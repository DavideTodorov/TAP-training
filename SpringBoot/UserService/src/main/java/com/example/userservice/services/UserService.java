package com.example.userservice.services;

import com.example.userservice.models.entities.*;
import com.example.userservice.models.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
    private final Gson gson;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final RestTemplate template;


    public UserService(Gson gson, ObjectMapper objectMapper, UserRepository userRepository) {
        this.gson = gson;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
        template = new RestTemplate();
    }

    public User createUser(UserRequestDTO userDTO) {
        if (checkIfUserExists(userDTO.getFirstName())) {
            throw new IllegalStateException("User does not exist");
        }

        User user = new User(userDTO.getFirstName(), userDTO.getLastName());

        String addressServiceUrl = String.format("http://localhost:8082/address/create?userId=%s", user.getId());
        Address address = template.postForObject(addressServiceUrl, null, Address.class);

        userRepository.save(user);

        return user;
    }


    public UserResponseDTO getUser(String firstName) {
        if (!checkIfUserExists(firstName)) {
            throw new IllegalStateException("User does not exist");
        }

        User user = userRepository.getUserByFirstName(firstName).get();

        String addressServiceUrl = String.format("http://localhost:8082/address/%s", user.getId());
        Address[] addressesForUse = template.getForObject(addressServiceUrl, Address[].class);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setAddresses(List.of(addressesForUse));

        return userResponseDTO;
    }


    public String createAddressForUser(String firstName) {


        return "userJson";
    }

    public String createTransaction(String firstName) {

        return "userJson";
    }

    public String updateUser(String firstName, UserRequestDTO userDTO) {

        return "userJson";
    }

    public String deleteUser(String firstName) {


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

//        User user = userRepository.getUserByFirstName(userFirstName).orElse(null);
//
//        user.getMovies().add(movie);
//        userRepository.save(user);
//
//        String userJson = "";
//        try {
//            userJson = objectMapper.writeValueAsString(user);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

        return "userJson";
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



