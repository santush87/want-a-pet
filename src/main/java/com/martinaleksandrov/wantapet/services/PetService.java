package com.martinaleksandrov.wantapet.services;

import com.martinaleksandrov.wantapet.models.dtos.PetCreatingDto;
import com.martinaleksandrov.wantapet.models.entities.PetEntity;
import com.martinaleksandrov.wantapet.models.enums.PetType;
import com.martinaleksandrov.wantapet.reporitories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final ModelMapper modelMapper;

    public void addDog(PetCreatingDto petCreatingDto) {
        PetEntity pet = addPet(petCreatingDto);
        pet.setType(PetType.DOG);
    }

    public void addCat(PetCreatingDto petCreatingDto) {
        PetEntity pet = addPet(petCreatingDto);
        pet.setType(PetType.CAT);
    }

    private PetEntity addPet(PetCreatingDto petCreatingDto) {
        return this.modelMapper.map(petCreatingDto, PetEntity.class);
    }
}
