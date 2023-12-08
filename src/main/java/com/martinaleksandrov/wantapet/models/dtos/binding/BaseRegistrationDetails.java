package com.martinaleksandrov.wantapet.models.dtos.binding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class BaseRegistrationDetails {

    //    @NotBlank
    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters!")
    private String firstName;

    //    @NotBlank
    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters!")
    private String lastName;

    @NotBlank(message = "Select a user type!")
    private String userType;

    //    @NotBlank
    @Size(min = 8, max = 20, message = "Phone number must be between 8 and 20 characters!")
    private String phoneNumber;

    @NotBlank(message = "Select a country!")
    private String country;

    //    @NotBlank
    @Size(min = 2, message = "City length must be at least 2 characters!")
    private String city;

    @NotBlank(message = "Enter a street!")
    private String street;

    private String streetNumber;

    public BaseRegistrationDetails setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public BaseRegistrationDetails setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public BaseRegistrationDetails setUserType(String userType) {
        this.userType = userType;
        return this;
    }

    public BaseRegistrationDetails setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public BaseRegistrationDetails setCountry(String country) {
        this.country = country;
        return this;
    }

    public BaseRegistrationDetails setCity(String city) {
        this.city = city;
        return this;
    }

    public BaseRegistrationDetails setStreet(String street) {
        this.street = street;
        return this;
    }

    public BaseRegistrationDetails setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }
}
