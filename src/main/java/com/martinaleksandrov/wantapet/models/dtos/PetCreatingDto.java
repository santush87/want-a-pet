package com.martinaleksandrov.wantapet.models.dtos;

import com.martinaleksandrov.wantapet.annotation.StringDateInPastOrPresent;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetCreatingDto {

    @NotNull
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters!")
    private String name;

    private String breed;

    @NotNull
    private String weight;

    @NotNull
    private String gender;

    @NotNull
    private String image;

    @PositiveOrZero
    @Max(30)
    private String age;

    @Size(max = 200, message = "Description cannot be more than 200 characters!")
    private String description;
}
