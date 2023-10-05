package com.martinaleksandrov.wantapet.models.dtos;

import com.martinaleksandrov.wantapet.models.entities.UserAddress;
import com.martinaleksandrov.wantapet.models.enums.CountryEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDto {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 2, max = 15)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 15)
    private String lastName;

    @NotBlank
    @Size(min = 6, max = 15)
    private String password;

    @NotBlank
    private String type;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String countryEnum;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    private int number;
}
