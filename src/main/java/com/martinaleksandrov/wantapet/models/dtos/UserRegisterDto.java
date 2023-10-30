package com.martinaleksandrov.wantapet.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UserRegisterDto {

    @Email
    @NotBlank(message = "Email cannot be empty!")
    private String email;

//    @NotBlank
    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters!")
    private String firstName;

//    @NotBlank
    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters!")
    private String lastName;

//    @NotBlank
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters!")
    private String password;

    private String confirmPassword;

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

//    @Positive(message = "Number should be a positive number")
    private int streetNumber;
}
