package com.example.transactionservice.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;

public class Configuration {

    @Bean
    public Gson getGson() {
        Gson gson = new Gson();

        return new Gson();
    }
}
