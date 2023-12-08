package com.martinaleksandrov.wantapet.models.dtos.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PetDetailsDto {

    private Long id;
    private String type;
    private String name;
    private String breed;
    private String weight;
    private String gender;
    private String age;
    private String description;
    private String image;
    private String ownersEmail;
    private boolean viewerIsOwner;
    private boolean canAdopt;

    public PetDetailsDto setId(Long id) {
        this.id = id;
        return this;
    }

    public PetDetailsDto setType(String type) {
        this.type = type;
        return this;
    }

    public PetDetailsDto setName(String name) {
        this.name = name;
        return this;
    }

    public PetDetailsDto setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public PetDetailsDto setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public PetDetailsDto setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public PetDetailsDto setAge(String age) {
        this.age = age;
        return this;
    }

    public PetDetailsDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public PetDetailsDto setImage(String image) {
        this.image = image;
        return this;
    }

    public PetDetailsDto setOwnersEmail(String ownersEmail) {
        this.ownersEmail = ownersEmail;
        return this;
    }

    public PetDetailsDto setViewerIsOwner(boolean viewerIsOwner) {
        this.viewerIsOwner = viewerIsOwner;
        return this;
    }

    public PetDetailsDto setCanAdopt(boolean canAdopt) {
        this.canAdopt = canAdopt;
        return this;
    }
}
