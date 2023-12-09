package com.martinaleksandrov.wantapet.services.impl;

import com.martinaleksandrov.wantapet.exceptions.UserNotFoundException;
import com.martinaleksandrov.wantapet.models.dtos.view.AdoptedPetsViewDto;
import com.martinaleksandrov.wantapet.models.dtos.view.PetsToSendDto;
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
import java.util.*;

@Service
@RequiredArgsConstructor
public class AdoptedPetsServiceImpl implements AdoptedPetsService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final AdoptionRepository adoptionRepository;

    @Override
    public void adoptPet(Long id, String ownerToBe) {

        PetEntity petForAdoption = this.petRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        petForAdoption.setAdopted(true);

        UserEntity newOwner = this.userRepository.findByEmail(ownerToBe)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        UserEntity prevOwner = this.userRepository.findById(petForAdoption.getOwner().getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

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

    @Override
    public List<AdoptedPetsViewDto> getAllMyAdoptedPets(String viewer) {

        UserEntity owner = this.userRepository.findByEmail(viewer)
                .orElseThrow(() -> new UsernameNotFoundException("The user with username: " + viewer + " is not found!"));
        List<AdoptedPetsViewDto> adoptedPets = new ArrayList<>();

        List<AdoptedPetsEntity> allByNewOwnerId =
                this.adoptionRepository.findAllByNewOwnerId(owner.getId());

        List<AdoptedPetsEntity> petsSortByDate = allByNewOwnerId.stream()
                .sorted(Comparator.comparing(AdoptedPetsEntity::getAdoptionDate)
                        .reversed())
                .toList();

        for (AdoptedPetsEntity pet : petsSortByDate) {

            AdoptedPetsViewDto petsViewDto = AdoptedPetsViewDto.builder()
                    .owner(owner.getName())
                    .petsBreed(pet.getPetsBreed())
                    .petsImage(pet.getPetsImage())
                    .age(pet.getAge())
                    .isSend(pet.isCompleteAdoption())
                    .petsName(pet.getPetsName())
                    .adoptionDate(pet.getAdoptionDate().toString())
                    .build();

            petsViewDto.setPrevOwner(pet.getPrevOwner().getName());

            adoptedPets.add(petsViewDto);
        }
        return adoptedPets;
    }

    @Override
    public List<String> findAllAdoptedPets() {

        List<String> allAdoptedPets = new ArrayList<>();
        List<AdoptedPetsEntity> all = this.adoptionRepository.findAllBy();

        System.out.println();
        for (AdoptedPetsEntity pet : all) {
            allAdoptedPets.add(pet.toString());
        }
        return allAdoptedPets;
    }

    @Override
    public List<PetsToSendDto> getPetsToSend(String username) {
        UserEntity owner = this.userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        List<AdoptedPetsEntity> allPets =
                this.adoptionRepository.findAllByPrevOwnerId(owner.getId());

        List<PetsToSendDto> petsToSend = new ArrayList<>();

        for (AdoptedPetsEntity pet : allPets) {
            UserEntity newOwner = pet.getNewOwner();
            PetsToSendDto pets = PetsToSendDto.builder()
                    .id(pet.getId())
                    .age(pet.getAge())
                    .petsName(pet.getPetsName())
                    .petsBreed(pet.getPetsBreed())
                    .petsImage(pet.getPetsImage())
                    .adoptionDate(pet.getAdoptionDate().toString())
                    .ownerName(newOwner.getName())
                    .ownerEmail(newOwner.getEmail())
                    .ownerPhone(newOwner.getPhoneNumber())
                    .ownerAddress(newOwner.getAddress().toString())
                    .isSend(pet.isCompleteAdoption())
                    .build();

            petsToSend.add(pets);
        }

        return petsToSend;
    }

    @Override
    public void sendPet(Long id) {
        Optional<AdoptedPetsEntity> optPet = this.adoptionRepository.findById(id);
        if (optPet.isPresent()) {
            AdoptedPetsEntity pet = optPet.get();
            pet.setCompleteAdoption(true);
            this.adoptionRepository.save(pet);
        }
    }
}
