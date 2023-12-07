package com.martinaleksandrov.wantapet.services.impl;

import com.martinaleksandrov.wantapet.models.dtos.view.AdoptedPetsViewDto;
import com.martinaleksandrov.wantapet.models.entities.AdoptedPetsEntity;
import com.martinaleksandrov.wantapet.models.entities.PetEntity;
import com.martinaleksandrov.wantapet.models.entities.UserEntity;
import com.martinaleksandrov.wantapet.reporitories.AdoptionRepository;
import com.martinaleksandrov.wantapet.reporitories.PetRepository;
import com.martinaleksandrov.wantapet.reporitories.UserRepository;
import com.martinaleksandrov.wantapet.services.AdoptedPetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        String prevOwner = petForAdoption.getOwner().getName();

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
        this.petRepository.deleteById(id);
    }

    @Override
    public List<AdoptedPetsViewDto> getAllMyAdoptedPets(String viewer) {

        UserEntity owner = this.userRepository.findByEmail(viewer)
                .orElseThrow(() -> new UsernameNotFoundException("The user with username: " + viewer + " is not found!"));
        List<AdoptedPetsViewDto> adoptedPets = new ArrayList<>();

        List<AdoptedPetsEntity> allByNewOwnerId =
                this.adoptionRepository.findAllByNewOwnerId(owner.getId());

        for (AdoptedPetsEntity pet : allByNewOwnerId) {

            AdoptedPetsViewDto petsViewDto = AdoptedPetsViewDto.builder()
                    .owner(owner.getName())
                    .petsBreed(pet.getPetsBreed())
                    .petsImage(pet.getPetsImage())
                    .age(pet.getAge())
                    .petsName(pet.getPetsName())
                    .adoptionDate(pet.getAdoptionDate().toString())
                    .build();

            petsViewDto.setPrevOwner(pet.getPrevOwner());

            adoptedPets.add(petsViewDto);
        }
        return adoptedPets;
    }
}
