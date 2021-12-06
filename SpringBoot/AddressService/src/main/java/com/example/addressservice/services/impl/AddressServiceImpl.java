package com.example.addressservice.services.impl;

import com.example.addressservice.models.Address;
import com.example.addressservice.services.AddressService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddressServiceImpl implements AddressService {

    private final HashMap<UUID, List<Address>> usersAddresses;
    private final Gson gson;

    public AddressServiceImpl(Gson gson) {
        this.gson = gson;
        this.usersAddresses = new HashMap<>();
    }

    @Override
    public String createAddress(UUID userId) {
        if (!usersAddresses.containsKey(userId)) {
            usersAddresses.put(userId, new ArrayList<>());
        }

        Random random = new Random();
        int randomNumber = random.nextInt(1001);

        Address address = new Address(String.format("%s street", randomNumber));
        usersAddresses.get(userId).add(address);

        return gson.toJson(address);
    }

    @Override
    public String getAddress(UUID userId) {
        if (!usersAddresses.containsKey(userId)) {
            return "No addresses for this user";
        }

        return gson.toJson(usersAddresses.get(userId));
    }
}
