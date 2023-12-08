package com.martinaleksandrov.wantapet.services.impl;

import com.martinaleksandrov.wantapet.models.dtos.binding.PetCreatingDto;
import com.martinaleksandrov.wantapet.models.dtos.view.PetDetailsDto;
import com.martinaleksandrov.wantapet.models.dtos.view.PetViewModelDto;
import com.martinaleksandrov.wantapet.models.entities.PetEntity;
import com.martinaleksandrov.wantapet.models.entities.UserEntity;
import com.martinaleksandrov.wantapet.models.enums.GenderEnum;
import com.martinaleksandrov.wantapet.models.enums.PetType;
import com.martinaleksandrov.wantapet.models.enums.WeightRange;
import com.martinaleksandrov.wantapet.reporitories.PetRepository;
import com.martinaleksandrov.wantapet.services.PetService;
import com.martinaleksandrov.wantapet.services.UserService;
import com.martinaleksandrov.wantapet.services.aop.WarnIfExecutionExceeds;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Override
    public void addDog(PetCreatingDto petCreatingDto, String owner) {
        addPet(petCreatingDto, PetType.DOG, owner);
    }

    @Override
    public void addCat(PetCreatingDto petCreatingDto, String owner) {
        addPet(petCreatingDto, PetType.CAT, owner);
    }

    public void addPet(PetCreatingDto petCreatingDto, PetType petType, String owner) {

        UserEntity theOwner = this.userService.findByEmail(owner)
                .orElseThrow(() -> new UsernameNotFoundException("User with email "
                        + owner + " not found!"));

        PetEntity pet = this.modelMapper.map(petCreatingDto, PetEntity.class);

        pet.setType(petType);
        pet.setOwner(theOwner);
        this.petRepository.save(pet);
    }

    @WarnIfExecutionExceeds(
            timeInMillis = 1000L
    )
    @Override
    public List<PetViewModelDto> getAllPets() {
        List<PetEntity> all = this.petRepository.findAll();
        return getPets(all);
    }

    @WarnIfExecutionExceeds(
            timeInMillis = 1000L
    )
    @Override
    public List<PetViewModelDto> getAllDogs() {
        List<PetEntity> allDogs = this.petRepository.findAllByType(PetType.DOG);
        return getPets(allDogs);
    }

    @WarnIfExecutionExceeds(
            timeInMillis = 1000L
    )
    @Override
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

    @Override
    public PetDetailsDto getPetDetails(Long id, String viewer) {
        PetEntity petEntity = this.petRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pet with id " + id + " not found!"));


        PetDetailsDto pet = this.modelMapper.map(petEntity, PetDetailsDto.class);
        pet.setWeight(petEntity.getWeight().name);

        UserEntity user = this.userService.findById(petEntity.getOwner().getId())
                .orElseThrow(() -> new UsernameNotFoundException("User with id: " + petEntity.getOwner().getId() + " not found!"));

        pet.setOwnersEmail(user.getEmail());

        boolean isOwner = isOwner(petEntity, viewer);
        pet.setCanAdopt(canAdopt(petEntity, viewer));
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

    @Override
    public boolean isOwner(Long id, String username) {
        return isOwner(
                this.petRepository.findById(id).orElse(null),
                username
        );
    }

    private boolean canAdopt(PetEntity pet, String username) {
        if (pet == null || username == null) {
            // anonymous users own no pets
            // missing pets are meaningless
            return false;
        }
        return !pet.getOwner().getEmail().equals(username);
    }

    @Override
    public List<PetViewModelDto> getAllMyPets(String viewer) {
        List<PetEntity> all = this.petRepository.findAll();
        List<PetViewModelDto> pets = getPets(all);
        List<PetViewModelDto> myPets = new ArrayList<>();
        for (PetViewModelDto pet : pets) {
            if (pet.getOwner().equals(viewer)) {
                myPets.add(pet);
            }
        }
        return myPets;
    }

    @Override
    public void deletePet(Long id, String viewersUsername) {
        boolean owner = isOwner(id, viewersUsername);
        if (owner) {
            this.petRepository.deleteById(id);
        }
    }

    @Override
    public void editPet(Long id, PetCreatingDto petCreatingDto) {
        Optional<PetEntity> optionalPet = this.petRepository.findById(id);
        if (optionalPet.isPresent()) {

            PetEntity pet = PetEntity.builder()
                    .name(petCreatingDto.getName())
                    .breed(petCreatingDto.getBreed())
                    .image(petCreatingDto.getImage())
                    .gender(GenderEnum.valueOf(petCreatingDto.getGender()))
                    .age(Integer.parseInt(petCreatingDto.getAge()))
                    .weight(WeightRange.valueOf(petCreatingDto.getWeight()))
                    .description(petCreatingDto.getDescription())
                    .build();

            this.petRepository.save(pet);
        }
    }

    @Override
    public PetEntity getPet(Long id) {
        return this.petRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No pet with id: " + id + " found!"));
    }
}

