package com.martinaleksandrov.wantapet.services.impl;

import com.martinaleksandrov.wantapet.models.dtos.PetCreatingDto;
import com.martinaleksandrov.wantapet.models.dtos.UserRegisterDto;
import com.martinaleksandrov.wantapet.models.entities.PetEntity;
import com.martinaleksandrov.wantapet.models.entities.UserEntity;
import com.martinaleksandrov.wantapet.models.enums.PetType;
import com.martinaleksandrov.wantapet.reporitories.PetRepository;
import com.martinaleksandrov.wantapet.services.PetService;
import com.martinaleksandrov.wantapet.testUtils.TestPetDataUtil;
import com.martinaleksandrov.wantapet.testUtils.TestUserDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class PetServiceImplTestIT {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetService petService;
    @Autowired
    private UserServiceImpl userService;

    private PetCreatingDto catDto = new PetCreatingDto();
    private PetCreatingDto dogDto = new PetCreatingDto();
    private UserRegisterDto userDto= new UserRegisterDto();


    @BeforeEach
    void setUp() {
        this.petRepository.deleteAll();
        catDto.setName("Pisa")
                .setBreed("Angorka")
                .setAge("3")
                .setDescription("Kote")
                .setGender("FEMALE")
                .setImage("sdfgsdfgsdffgdfg")
                .setWeight("BETWEEN_0KG_AND_5KG");

        dogDto.setName("Sharo")
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

        Assertions.assertEquals(2, this.petService.getAllPets().size());

        petService.addDog(dogo, userDto.getEmail());
        Assertions.assertEquals(3, this.petService.getAllPets().size());
    }
}