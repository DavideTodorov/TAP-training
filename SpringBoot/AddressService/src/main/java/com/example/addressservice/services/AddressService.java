package com.example.addressservice.services;

import com.example.addressservice.models.Address;
import com.example.addressservice.repositories.AddressRepository;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address createAddress(UUID userId) {

        Random random = new Random();
        int i = random.nextInt(1001);
        Address address = new Address(String.format("%d street", i), userId);

        addressRepository.save(address);


        return address;
    }

    public String getAddress(UUID userId) {

        return "gson.toJson(usersAddresses.get(userId))";
    }
}
