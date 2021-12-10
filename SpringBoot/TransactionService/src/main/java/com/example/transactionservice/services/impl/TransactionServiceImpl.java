package com.example.transactionservice.services.impl;

import com.example.transactionservice.models.Transaction;
import com.example.transactionservice.services.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final Gson gson;
    private final Map<UUID, List<Transaction>> transactionMap;

    public TransactionServiceImpl(Gson gson) {
        this.gson = gson;
        this.transactionMap = new HashMap<>();
    }


    @Override
    public String createTransaction(UUID userId) {
        String transactionApiUrl = "http://localhost:8000/info/generate/id";

        RestTemplate template = new RestTemplate();

        String result = "error";
        for (int i = 0; i < 10; i++) {
            try {
                result = template.getForObject(transactionApiUrl, String.class);
                break;
            } catch (HttpServerErrorException.InternalServerError e) {
                System.out.println("Try again!");
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, String> map = null;
        try {
            map = objectMapper.readValue(result, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        LocalDateTime dateOfExecution = LocalDateTime.parse(map.get("dateOfExecution"), dtf);

        Transaction transaction = new Transaction();
        transaction.setId(UUID.fromString(map.get("id")));
        transaction.setDateOfExecution(dateOfExecution);

        transactionMap.putIfAbsent(userId, new ArrayList<>());
        transactionMap.get(userId).add(transaction);

        return result;
    }

    @Override
    public String getAll(UUID userId) {
        transactionMap.get(userId);
        return gson.toJson(transactionMap.get(userId));
    }
}
