package com.martinaleksandrov.wantapet.services;

import com.martinaleksandrov.wantapet.models.dtos.PetCreatingDto;
import com.martinaleksandrov.wantapet.models.dtos.PetDetailsDto;
import com.martinaleksandrov.wantapet.models.dtos.PetViewModelDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface PetService {

    void addDog(PetCreatingDto petCreatingDto, UserDetails owner);

    void addCat(PetCreatingDto petCreatingDto, UserDetails owner);

    List<PetViewModelDto> getAllPets();

    List<PetViewModelDto> getAllDogs();

    List<PetViewModelDto> getAllCats();

    PetDetailsDto getPetDetails(Long id, UserDetails viewer);

    boolean isOwner(Long id, String username);

    List<PetViewModelDto> getAllMyPets(UserDetails viewer);

    void deletePet(Long id);

    void editPet(Long id, PetCreatingDto petCreatingDto);
}
