package com.example.addressservice.models;

import java.util.UUID;

public class Address {
    private UUID id;
    private String streetName;

    public Address(String streetName) {
        this.id = UUID.randomUUID();
        this.streetName = streetName;
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
}
