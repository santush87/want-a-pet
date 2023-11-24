package com.martinaleksandrov.wantapet.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PetViewModelDto {
    private Long id;
    private String type;
    private String name;
    private String age;
    private String image;
    private String owner;

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

    public PetViewModelDto setAge(String age) {
        this.age = age;
        return this;
    }

    public PetViewModelDto setImage(String image) {
        this.image = image;
        return this;
    }

    public PetViewModelDto setOwner(String owner) {
        this.owner = owner;
        return this;
    }
}

