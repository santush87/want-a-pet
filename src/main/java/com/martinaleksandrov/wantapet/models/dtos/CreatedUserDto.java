package com.martinaleksandrov.wantapet.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatedUserDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String token;
}
