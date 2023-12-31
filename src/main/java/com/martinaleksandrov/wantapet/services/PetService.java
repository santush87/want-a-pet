package com.martinaleksandrov.wantapet.services;

import com.martinaleksandrov.wantapet.models.dtos.binding.PetCreatingDto;
import com.martinaleksandrov.wantapet.models.dtos.view.PetDetailsDto;
import com.martinaleksandrov.wantapet.models.dtos.view.PetViewModelDto;
import com.martinaleksandrov.wantapet.models.entities.PetEntity;

import java.util.List;

public interface PetService {

    void addDog(PetCreatingDto petCreatingDto, String owner);

    void addCat(PetCreatingDto petCreatingDto, String owner);

    List<PetViewModelDto> getAllPets();

    List<PetViewModelDto> getAllDogs();

    List<PetViewModelDto> getAllCats();

    PetDetailsDto getPetDetails(Long id, String viewer);

    boolean isOwner(Long id, String username);

    List<PetViewModelDto> getAllMyPets(String viewer);

    void deletePet(Long id, String viewersUsername);

    void editPet(Long id, PetCreatingDto petCreatingDto);

    PetEntity getPet(Long id);
}
