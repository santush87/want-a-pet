package com.martinaleksandrov.wantapet.models.dtos;

import com.martinaleksandrov.wantapet.annotation.StringDateInPastOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetCreatingDto {

    private String breed;

    @NotNull
    private String image;

    @StringDateInPastOrPresent
    private String birthOn;

    private String description;
}
