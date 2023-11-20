package com.martinaleksandrov.wantapet.services;

import com.martinaleksandrov.wantapet.models.dtos.PetCreatingDto;
import com.martinaleksandrov.wantapet.models.dtos.PetViewModelDto;
import com.martinaleksandrov.wantapet.models.entities.PetEntity;
import com.martinaleksandrov.wantapet.models.entities.UserEntity;
import com.martinaleksandrov.wantapet.models.enums.PetType;
import com.martinaleksandrov.wantapet.reporitories.PetRepository;
import com.martinaleksandrov.wantapet.reporitories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        UserEntity theOwner = this.userRepository.findByEmail(owner.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User with email "
                        + owner.getUsername() + " not found!"));

        PetEntity pet = this.modelMapper.map(petCreatingDto, PetEntity.class);

        pet.setType(petType);
        pet.setOwner(theOwner);
        this.petRepository.save(pet);
    }

    public List<PetViewModelDto> getAllPets() {
        List<PetEntity> all = this.petRepository.findAll();
        List<PetViewModelDto> toView = new ArrayList<>();
        for (PetEntity pet : all) {
            PetViewModelDto petViewModelDto = this.modelMapper.map(pet, PetViewModelDto.class);
            toView.add(petViewModelDto);
        }
        return toView;
    }
}
