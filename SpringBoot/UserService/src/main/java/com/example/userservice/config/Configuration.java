package com.example.userservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;

public class Configuration {

    @Bean
    public Gson getGson() {
        return new Gson();
    }

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }
}
