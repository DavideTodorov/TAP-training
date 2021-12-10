package com.example.userservice.models.repositories;

import com.example.userservice.models.entities.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AddressRepository extends CrudRepository<Address, UUID> {
}
