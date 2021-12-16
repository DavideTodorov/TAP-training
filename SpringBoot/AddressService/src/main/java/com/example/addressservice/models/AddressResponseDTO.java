package com.example.addressservice.models;

import java.util.UUID;

public class AddressResponseDTO {

    private UUID id;
    private String streetName;

    public AddressResponseDTO(UUID id, String streetName) {
        this.id = id;
        this.streetName = streetName;
    }

    public AddressResponseDTO() {
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
