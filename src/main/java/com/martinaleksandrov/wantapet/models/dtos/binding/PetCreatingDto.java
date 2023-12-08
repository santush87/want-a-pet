package com.martinaleksandrov.wantapet.models.dtos.binding;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PetCreatingDto {

    @NotNull
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters!")
    private String name;

    @NotNull
    @Size(min = 2, message = "Breed must be at least 2 characters!")
    private String breed;

    @NotNull(message = "Please choose a weight")
    private String weight;

    @NotNull(message = "Please choose a gender")
    private String gender;

    @NotNull(message = "Please enter a image url!")
    private String image;

    @Min(0)
    @Max(value = 30, message = "Age must be between 0 and 30!")
    private String age;

    @Size(max = 200, message = "Description cannot be more than 200 characters!")
    private String description;

    public PetCreatingDto setName(String name) {
        this.name = name;
        return this;
    }

    public PetCreatingDto setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public PetCreatingDto setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public PetCreatingDto setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public PetCreatingDto setImage(String image) {
        this.image = image;
        return this;
    }

    public PetCreatingDto setAge(String age) {
        this.age = age;
        return this;
    }

    public PetCreatingDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
