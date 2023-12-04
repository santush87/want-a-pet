package com.martinaleksandrov.wantapet.services.impl;

import com.martinaleksandrov.wantapet.models.dtos.PetCreatingDto;
import com.martinaleksandrov.wantapet.models.dtos.PetDetailsDto;
import com.martinaleksandrov.wantapet.models.dtos.PetViewModelDto;
import com.martinaleksandrov.wantapet.models.dtos.UserRegisterDto;
import com.martinaleksandrov.wantapet.models.entities.PetEntity;
import com.martinaleksandrov.wantapet.reporitories.PetRepository;
import com.martinaleksandrov.wantapet.services.PetService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PetServiceImplTestIT {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetService petService;
    @Autowired
    private UserServiceImpl userService;
    private PetEntity cat = new PetEntity();
    private PetEntity dog = new PetEntity();
    private UserRegisterDto userDto = new UserRegisterDto();


    @BeforeEach
    void setUp() {
        this.petRepository.deleteAll();
        PetCreatingDto catDto = new PetCreatingDto()
                .setName("Pisa")
                .setBreed("Angorka")
                .setAge("3")
                .setDescription("Kote")
                .setGender("FEMALE")
                .setImage("sdfgsdfgsdffgdfg")
                .setWeight("BETWEEN_0KG_AND_5KG");
        PetCreatingDto dogDto = new PetCreatingDto()
                .setName("Sharo")
                .setBreed("Husky")
                .setAge("14")
                .setDescription("Kucho")
                .setGender("MALE")
                .setImage("sdfgsdfgsdffs")
                .setWeight("UP_25KG");

        userDto.setPassword("test1234")
                .setConfirmPassword("test1234");
        userDto.setEmail("email@email.com")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setUserType("ORGANIZATION")
                .setPhoneNumber("0123456789")
                .setCountry("BULGARIA")
                .setCity("Sofia")
                .setStreet("ulica")
                .setStreetNumber("5");

        this.userService.register(userDto);

        this.petService.addCat(catDto, userDto.getEmail());
        this.petService.addDog(dogDto, userDto.getEmail());

        cat = this.petRepository.findByName(catDto.getName());
        dog = this.petRepository.findByName(dogDto.getName());
    }

    @AfterEach
    void tearDown() {
        petRepository.deleteAll();
    }

    @Test
    void testAddDog() {
        PetCreatingDto dogo = new PetCreatingDto();
        dogo.setName("Sharo")
                .setBreed("Husky")
                .setAge("14")
                .setDescription("Kucho")
                .setGender("MALE")
                .setImage("sdfgsdfgsdffs")
                .setWeight("UP_25KG");

        assertEquals(2, this.petService.getAllPets().size());

        petService.addDog(dogo, userDto.getEmail());
        assertEquals(3, this.petService.getAllPets().size());
    }

    @Test
    void testGetPetsDetails() {
        PetDetailsDto catDetails = this.petService.getPetDetails(cat.getId(), userDto.getEmail());

        assertEquals(catDetails.getName(), cat.getName(), "Names check!");
        assertEquals(catDetails.getBreed(), cat.getBreed(), "Breed check!");
    }

    @Test
    void testGetPetDetailsThrows() {
        assertThrows(NoSuchElementException.class,
                () -> this.petService.getPetDetails(4L, "Pesho"));
    }

    @Test
    void testGetAllDogs() {
        List<PetViewModelDto> allDogs = this.petService.getAllDogs();
        assertEquals(1, allDogs.size(), "Check the count of all dogs!");
    }

    @Test
    void testGetAllCats() {
        List<PetViewModelDto> allCats = this.petService.getAllCats();
        assertEquals(1, allCats.size(), "Check the count of all cats!");
    }

    @Test
    void testGetAllPets() {
        List<PetViewModelDto> allMyPets = this.petService.getAllMyPets(userDto.getEmail());
        assertEquals(2, allMyPets.size(), "Check the count of all pets!");
    }

//    @Test
//    void testDeletePet() {
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userDto.getEmail());
//
//        List<PetViewModelDto> allMyPets = this.petService.getAllMyPets(userDetails.getUsername());
//        assertEquals(2, allMyPets.size(), "Checking before delete");
//        this.petService.deletePet(cat.getId(), userDetails.getUsername());
//        assertEquals(1, allMyPets.size(), "Checking after delete");
//    }
}