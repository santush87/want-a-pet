package com.martinaleksandrov.wantapet.services.impl;

import com.martinaleksandrov.wantapet.models.entities.AdoptedPetsEntity;
import com.martinaleksandrov.wantapet.models.entities.PetEntity;
import com.martinaleksandrov.wantapet.models.entities.UserEntity;
import com.martinaleksandrov.wantapet.reporitories.AdoptionRepository;
import com.martinaleksandrov.wantapet.reporitories.PetRepository;
import com.martinaleksandrov.wantapet.reporitories.UserRepository;
import com.martinaleksandrov.wantapet.services.AdoptedPetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AdoptedPetsServiceImpl implements AdoptedPetsService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final AdoptionRepository adoptionRepository;

    @Override
    public void adoptPet(Long id, String ownerToBe) {

        PetEntity petForAdoption = this.petRepository.findById(id).orElseThrow();
        UserEntity newOwner = this.userRepository.findByEmail(ownerToBe).orElseThrow();
        UserEntity prevOwner = petForAdoption.getOwner();

        AdoptedPetsEntity adoptedPetsEntity = AdoptedPetsEntity.builder()
                .petsName(petForAdoption.getName())
                .age(petForAdoption.getAge())
                .petsBreed(petForAdoption.getBreed())
                .petsImage(petForAdoption.getImage())
                .newOwner(newOwner)
                .prevOwner(prevOwner)
                .adoptionDate(LocalDate.now())
                .build();

        this.adoptionRepository.save(adoptedPetsEntity);
    }
}
