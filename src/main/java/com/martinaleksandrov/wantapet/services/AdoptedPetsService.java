package com.martinaleksandrov.wantapet.services;

import com.martinaleksandrov.wantapet.models.dtos.view.AdoptedPetsViewDto;

import java.util.List;

public interface AdoptedPetsService {
    void adoptPet(Long id, String newOwner);
    List<AdoptedPetsViewDto> getAllMyAdoptedPets(String viewer);
}
