package com.martinaleksandrov.wantapet.models.dtos.view;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class AdoptedPetsViewDto {
    private String petsName;
    private String petsBreed;
    private String petsImage;
    private int age;
    private String owner;
    private String prevOwner;
    private String adoptionDate;
}
