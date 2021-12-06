package com.example.transactionservice.services.impl;

import com.example.transactionservice.services.TransactionService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final Gson gson;
    private final Map<UUID, List<String>> transactionMap;

    public TransactionServiceImpl(Gson gson) {
        this.gson = gson;
        this.transactionMap = new HashMap<>();
    }


    @Override
    public String createTransaction(UUID userId) {
        String addressServiceUrl = "http://localhost:8000/info/generate/id";

        RestTemplate template = new RestTemplate();

        String result = "error";
        for (int i = 0; i < 10; i++) {
            try {
                result = template.getForObject(addressServiceUrl, String.class);
                break;
            } catch (HttpServerErrorException.InternalServerError e) {
                System.out.println("Try again!");
            }
        }

        transactionMap.putIfAbsent(userId, new ArrayList<>());
        transactionMap.get(userId).add(result);

        return result;
    }
}
