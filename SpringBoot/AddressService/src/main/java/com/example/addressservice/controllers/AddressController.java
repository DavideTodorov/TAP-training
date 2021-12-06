package com.example.addressservice.controllers;

import com.example.addressservice.models.Address;
import com.example.addressservice.services.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @GetMapping("/address/create")
    public String createAddress(@RequestParam Long userId){
        String address = addressService.createAddress(userId);
        return address;
    }
}
