package com.martinaleksandrov.wantapet.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PetViewModelDto {
    private Long id;
    private String type;
    private String name;
    private String breed;
    private String weight;
    private String gender;
    private String age;
    private String description;
    private String image;

    public PetViewModelDto setId(Long id) {
        this.id = id;
        return this;
    }

    public PetViewModelDto setType(String type) {
        this.type = type;
        return this;
    }

    public PetViewModelDto setName(String name) {
        this.name = name;
        return this;
    }

    public PetViewModelDto setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public PetViewModelDto setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public PetViewModelDto setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public PetViewModelDto setAge(String age) {
        this.age = age;
        return this;
    }

    public PetViewModelDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public PetViewModelDto setImage(String image) {
        this.image = image;
        return this;
    }
}

