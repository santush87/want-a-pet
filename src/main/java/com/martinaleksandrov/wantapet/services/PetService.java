package com.martinaleksandrov.wantapet.services;

import com.martinaleksandrov.wantapet.models.dtos.PetCreatingDto;
import com.martinaleksandrov.wantapet.models.dtos.PetDetailsDto;
import com.martinaleksandrov.wantapet.models.dtos.PetViewModelDto;
import com.martinaleksandrov.wantapet.models.entities.PetEntity;
import com.martinaleksandrov.wantapet.models.entities.UserEntity;
import com.martinaleksandrov.wantapet.models.enums.PetType;
import com.martinaleksandrov.wantapet.reporitories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public void addDog(PetCreatingDto petCreatingDto, UserDetails owner) {
        addPet(petCreatingDto, PetType.DOG, owner);
    }

    public void addCat(PetCreatingDto petCreatingDto, UserDetails owner) {
        addPet(petCreatingDto, PetType.CAT, owner);
    }

    private void addPet(PetCreatingDto petCreatingDto, PetType petType, UserDetails owner) {

        UserEntity theOwner = this.userService.findByEmail(owner.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User with email "
                        + owner.getUsername() + " not found!"));

        PetEntity pet = this.modelMapper.map(petCreatingDto, PetEntity.class);

        pet.setType(petType);
        pet.setOwner(theOwner);
        this.petRepository.save(pet);
    }

    /***    PETS IN PAGES    ***/
//    public Page<PetViewModelDto> getAllPets(Pageable pageable) {
//
//        return this.petRepository.findAll(pageable)
//                .map(pet -> this.modelMapper.map(pet, PetViewModelDto.class));
//    }

//    public Page<PetViewModelDto> getAllDogs(Pageable pageable) {
////        List<PetEntity> allDogs = this.petRepository.findAllByType(PetType.DOG);
//        return this.petRepository.findAllByTypeDog()
//                .map(pet -> this.modelMapper.map(pet, PetViewModelDto.class));
//    }

    /***    PETS IN LIST    ***/
    public List<PetViewModelDto> getAllPets() {
        List<PetEntity> all = this.petRepository.findAll();
        return getPets(all);
    }

    public List<PetViewModelDto> getAllDogs() {
        List<PetEntity> allDogs = this.petRepository.findAllByType(PetType.DOG);
        return getPets(allDogs);
    }

    public List<PetViewModelDto> getAllCats() {
        List<PetEntity> allCats = this.petRepository.findAllByType(PetType.CAT);
        return getPets(allCats);
    }

    private List<PetViewModelDto> getPets(List<PetEntity> pets) {
        List<PetViewModelDto> toView = new ArrayList<>();
        for (PetEntity pet : pets) {
            PetViewModelDto petViewModelDto = this.modelMapper.map(pet, PetViewModelDto.class);
            petViewModelDto.setOwner(pet.getOwner().getEmail());
            toView.add(petViewModelDto);
        }
        return toView;
    }

    public PetDetailsDto getPetDetails(Long id, UserDetails viewer) {
        Optional<PetEntity> optionalPet = this.petRepository.findById(id);

        if (optionalPet.isEmpty()) {
            throw new NoSuchElementException("Pet with id " + id + " not found!");
        }

        PetDetailsDto pet = this.modelMapper.map(optionalPet, PetDetailsDto.class);
        pet.setWeight(optionalPet.get().getWeight().name.toString());

        Optional<UserEntity> user = this.userService.findById(optionalPet.get().getOwner().getId());

        pet.setOwnersEmail(user.get().getEmail());

        boolean isOwner = isOwner(optionalPet.get(), viewer.getUsername());
        pet.setCanAdopt(canAdopt(optionalPet.get(), viewer.getUsername()));
        pet.setViewerIsOwner(isOwner);

        return pet;
    }

    private boolean isOwner(PetEntity pet, String username) {
        if (pet == null || username == null) {
            // anonymous users own no pets
            // missing pets are meaningless
            return false;
        }

        UserEntity viewerEntity = userService.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Unknown user..."));

        if (this.userService.isAdmin(viewerEntity)) {
            // all admins own all offers
            return true;
        }

        return Objects.equals(
                pet.getOwner().getId(),
                viewerEntity.getId());
    }

    private boolean canAdopt(PetEntity pet, String username) {
        if (pet == null || username == null) {
            // anonymous users own no pets
            // missing pets are meaningless
            return false;
        }
        if (!pet.getOwner().getEmail().equals(username)) {
            return true;
        }
        return false;
    }

    public List<PetViewModelDto> getAllMyPets(UserDetails viewer) {
        List<PetEntity> all = this.petRepository.findAll();
        List<PetViewModelDto> pets = getPets(all);
        List<PetViewModelDto> myPets = new ArrayList<>();
        for (PetViewModelDto pet : pets) {
            if (pet.getOwner().equals(viewer.getUsername())) {
                myPets.add(pet);
            }
        }
        return myPets;
    }
}
