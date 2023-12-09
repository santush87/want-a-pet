package com.martinaleksandrov.wantapet.models.dtos.view;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class PetsToSendDto {

    private long id;
    private String petsName;
    private String petsBreed;
    private String petsImage;
    private int age;
    private String ownerName;
    private String ownerPhone;
    private String ownerEmail;
    private String ownerAddress;
    private String adoptionDate;
    private boolean isSend = false;
}
