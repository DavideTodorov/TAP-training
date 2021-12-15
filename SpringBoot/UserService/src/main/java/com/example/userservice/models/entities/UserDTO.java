package com.example.userservice.models.entities;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class UserDTO {


    @NotBlank
    private String firstName;
    private String lastName;

    public UserDTO() {
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
