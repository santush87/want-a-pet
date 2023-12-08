package com.martinaleksandrov.wantapet.models.dtos.view;

import lombok.Getter;

@Getter
public class UserDetailsDto {

    private String id;

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private String userType;

    private String phoneNumber;

    private String country;

    private String city;

    private String street;

    private String streetNumber;

    private String joinedOn;

    private String user;

    private int uploadedPets;

    private int adoptedPets;

    public UserDetailsDto setId(String id) {
        this.id = id;
        return this;
    }

    public UserDetailsDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDetailsDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserDetailsDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserDetailsDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDetailsDto setUserType(String userType) {
        this.userType = userType;
        return this;
    }

    public UserDetailsDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserDetailsDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public UserDetailsDto setCity(String city) {
        this.city = city;
        return this;
    }

    public UserDetailsDto setStreet(String street) {
        this.street = street;
        return this;
    }

    public UserDetailsDto setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public UserDetailsDto setJoinedOn(String joinedOn) {
        this.joinedOn = joinedOn;
        return this;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public UserDetailsDto setUser(String user) {
        this.user = user;
        return this;
    }

    public UserDetailsDto setUploadedPets(int uploadedPets) {
        this.uploadedPets = uploadedPets;
        return this;
    }

    public UserDetailsDto setAdoptedPets(int adoptedPets) {
        this.adoptedPets = adoptedPets;
        return this;
    }
}
