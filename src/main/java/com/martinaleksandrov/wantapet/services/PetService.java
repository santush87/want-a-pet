package com.martinaleksandrov.wantapet.services;

import com.martinaleksandrov.wantapet.models.dtos.PetCreatingDto;
import com.martinaleksandrov.wantapet.models.entities.PetEntity;
import com.martinaleksandrov.wantapet.models.entities.UserEntity;
import com.martinaleksandrov.wantapet.models.enums.PetType;
import com.martinaleksandrov.wantapet.reporitories.PetRepository;
import com.martinaleksandrov.wantapet.reporitories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public void addDog(PetCreatingDto petCreatingDto, UserDetails owner) {
        addPet(petCreatingDto, PetType.DOG, owner);
    }

    public void addCat(PetCreatingDto petCreatingDto, UserDetails owner) {
        addPet(petCreatingDto, PetType.CAT, owner);
    }

    private void addPet(PetCreatingDto petCreatingDto, PetType petType, UserDetails owner) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity theOwner = this.userRepository.findByEmail(owner.getUsername()).get();

        PetEntity pet = this.modelMapper.map(petCreatingDto, PetEntity.class);

        pet.setType(petType);
        pet.setOwner(theOwner);
        this.petRepository.save(pet);

//        theOwner.getPets().add(pet);
//        this.userRepository.save(theOwner);
    }
}
