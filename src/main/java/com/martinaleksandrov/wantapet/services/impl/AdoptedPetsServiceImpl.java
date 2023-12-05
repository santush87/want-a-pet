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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdoptedPetsServiceImpl implements AdoptedPetsService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final AdoptionRepository adoptionRepository;

    @Override
    public void adoptPet(Long id, String ownerToBe) {
        AdoptedPetsEntity adoptedPetsEntity = new AdoptedPetsEntity();

        PetEntity petForAdoption = this.petRepository.findById(id).get();
        Optional<UserEntity> newOwner = this.userRepository.findByEmail(ownerToBe);
        UserEntity prevOwner = petForAdoption.getOwner();

        adoptedPetsEntity.setPetsName(petForAdoption.getName());
        adoptedPetsEntity.setPetsBreed(petForAdoption.getBreed());
        adoptedPetsEntity.setPetsImage(petForAdoption.getImage());
        adoptedPetsEntity.setNewOwner(newOwner.get());
        adoptedPetsEntity.setPrevOwner(prevOwner);

        this.adoptionRepository.save(adoptedPetsEntity);
    }

//    @Override
//    public int getAdoptedPetsCount(String username) {
//        Optional<UserEntity> user = this.userService.findByEmail(username);
//        List<AdoptedPetsEntity> allByNewOwnerId = this.adoptionRepository.findAllByNewOwnerId(user.get().getEmail());
//        return allByNewOwnerId.size();
//    }
//
//    @Override
//    public int getUploadedPetsCount(String username) {
//        return this.petService.getAllMyPets(username).size();
//    }
}
