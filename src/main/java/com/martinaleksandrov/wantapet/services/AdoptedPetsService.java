package com.martinaleksandrov.wantapet.services;

import com.martinaleksandrov.wantapet.models.dtos.view.AdoptedPetsViewDto;
import com.martinaleksandrov.wantapet.models.dtos.view.PetsToSendDto;

import java.util.List;

public interface AdoptedPetsService {
    void adoptPet(Long id, String newOwner);
    List<AdoptedPetsViewDto> getAllMyAdoptedPets(String viewer);

    List<String> findAllAdoptedPets();

    List<PetsToSendDto> getPetsToSend(String username);

    void sendPet(Long id);
}
