package com.example.addressservice.models;

import java.util.UUID;

public class Address {
    private UUID id;
    private UUID userId;
    private String streetName;

    public Address(String streetName, UUID userId) {
        this.id = UUID.randomUUID();
        this.streetName = streetName;
        this.userId = userId;
    }

    public Address(){

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
