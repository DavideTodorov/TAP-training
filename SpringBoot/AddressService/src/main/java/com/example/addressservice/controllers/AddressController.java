package com.example.addressservice.controllers;

import com.example.addressservice.models.Address;
import com.example.addressservice.models.AddressResponseDTO;
import com.example.addressservice.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<List<AddressResponseDTO>> getAddress(@PathVariable UUID userId) {
        List<AddressResponseDTO> addressesForUser = addressService.getAddress(userId);
        return ResponseEntity.ok(addressesForUser);
    }
}
