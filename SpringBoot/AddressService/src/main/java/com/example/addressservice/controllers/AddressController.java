package com.example.addressservice.controllers;

import com.example.addressservice.models.Address;
import com.example.addressservice.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/address/create")
    public ResponseEntity<Address> createAddress(@RequestParam UUID userId) {
        Address address = addressService.createAddress(userId);

        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @GetMapping("/address/{userId}")
    public String getAddress(@PathVariable UUID userId) {
        String address = addressService.getAddress(userId);
        return address;
    }
}
