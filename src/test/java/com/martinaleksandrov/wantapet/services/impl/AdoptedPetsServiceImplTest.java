package com.martinaleksandrov.wantapet.services.impl;

import com.martinaleksandrov.wantapet.models.dtos.binding.PetCreatingDto;
import com.martinaleksandrov.wantapet.models.dtos.binding.UserRegisterDto;
import com.martinaleksandrov.wantapet.models.dtos.view.AdoptedPetsViewDto;
import com.martinaleksandrov.wantapet.models.entities.PetEntity;
import com.martinaleksandrov.wantapet.reporitories.AdoptionRepository;
import com.martinaleksandrov.wantapet.reporitories.PetRepository;
import com.martinaleksandrov.wantapet.reporitories.UserRepository;
import com.martinaleksandrov.wantapet.services.AdoptedPetsService;
import com.martinaleksandrov.wantapet.services.PetService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AdoptedPetsServiceImplTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private AdoptionRepository adoptionRepository;
    @Autowired
    private AdoptedPetsService adoptedPetsService;
    @Autowired
    private PetService petService;
    @Autowired
    private UserServiceImpl userService;

    private PetEntity cat = new PetEntity();
    private UserRegisterDto prevOwner = new UserRegisterDto();
    private UserRegisterDto newOwner = new UserRegisterDto();

    @BeforeEach
    void setUp(){
        this.petRepository.deleteAll();
        // Creating new pet
        PetCreatingDto catDto = new PetCreatingDto()
                .setName("Pisa")
                .setBreed("Angorka")
                .setAge("3")
                .setDescription("Kote")
                .setGender("FEMALE")
                .setImage("sdfgsdfgsdffgdfg")
                .setWeight("BETWEEN_0KG_AND_5KG");

        // creating the previous owner
        prevOwner.setPassword("test1234")
                .setConfirmPassword("test1234");
        prevOwner.setEmail("prevUser@email.com")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setUserType("ORGANIZATION")
                .setPhoneNumber("0123456789")
                .setCountry("BULGARIA")
                .setCity("Sofia")
                .setStreet("ulica")
                .setStreetNumber("5");

        this.userService.register(prevOwner);

        // creating the new owner
        newOwner.setPassword("test1234")
                .setConfirmPassword("test1234");
        newOwner.setEmail("newUser@email.com")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setUserType("ORGANIZATION")
                .setPhoneNumber("0123456789")
                .setCountry("BULGARIA")
                .setCity("Sofia")
                .setStreet("ulica")
                .setStreetNumber("5");

        this.userService.register(newOwner);

        // Adding the cat with prevOwner
        this.petService.addCat(catDto, prevOwner.getEmail());

        cat = this.petRepository.findByName(catDto.getName());
    }

    @AfterEach
    void tearDown() {
        this.adoptionRepository.deleteAll();
        this.petRepository.deleteAll();
    }

    @Test
    void testAdoptPet(){
        assertEquals(0, this.adoptionRepository.count());

        this.adoptedPetsService.adoptPet(cat.getId(), newOwner.getEmail());
        assertEquals(1, this.adoptionRepository.count());

    }

    @Test
    void testGetAllMyAdoptedPets(){
        this.adoptedPetsService.adoptPet(cat.getId(), newOwner.getEmail());

        List<AdoptedPetsViewDto> petsNewOwner = this.adoptedPetsService.getAllMyAdoptedPets(newOwner.getEmail());
        List<AdoptedPetsViewDto> petsPrevOwner = this.adoptedPetsService.getAllMyAdoptedPets(prevOwner.getEmail());

        assertEquals(0, petsPrevOwner.size(), "Testing that the previous owner does not have any adopted pets!");
        assertEquals(1, petsNewOwner.size(), "The new owner should have 1 adopted pet!");
    }

    @Test
    void testFindAllAdoptedPets(){

        assertEquals(0, this.adoptedPetsService.findAllAdoptedPets().size(), "Testing before any pet were adopted!");
        this.adoptedPetsService.adoptPet(cat.getId(), newOwner.getEmail());
        assertEquals(1, this.adoptedPetsService.findAllAdoptedPets().size(), "Testing after a pet was adopted!");
    }
}