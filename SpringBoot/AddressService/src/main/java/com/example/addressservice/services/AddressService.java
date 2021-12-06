package com.example.addressservice.services;


import java.util.UUID;

public interface AddressService {

    String createAddress(UUID userId);

    String getAddress(UUID userId);
}
