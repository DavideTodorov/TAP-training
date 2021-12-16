package com.example.addressservice.repositories;

import com.example.addressservice.models.Address;
import com.example.addressservice.models.AddressResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {

    List<Address> findAllByUserId(UUID userId);
}
