package com.martinaleksandrov.wantapet.services.impl;

import com.martinaleksandrov.wantapet.models.dtos.binding.PetCreatingDto;
import com.martinaleksandrov.wantapet.models.dtos.view.PetDetailsDto;
import com.martinaleksandrov.wantapet.models.dtos.view.PetViewModelDto;
import com.martinaleksandrov.wantapet.models.entities.PetEntity;
import com.martinaleksandrov.wantapet.services.PetService;
import com.martinaleksandrov.wantapet.testUtils.TestPetDataUtil;
import com.martinaleksandrov.wantapet.testUtils.TestUserDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PetServiceImplTestIT {

    @Autowired
    private PetService petService;
    @Autowired
    private TestUserDataUtil userDataUtil;
    @Autowired
    private TestPetDataUtil petDataUtil;

    private static final String TEST_USER_EMAIL = "user@example.com";
    private static final String TEST_USER1_EMAIL = "user1@example.com";
    private static final String TEST_ADMIN_EMAIL = "admin@example.com";
    private static final String TEST_DOG_NAME = "Sharo";
    private static final String TEST_CAT_NAME = "Maca";
    private PetEntity cat = new PetEntity();
    private PetEntity dog = new PetEntity();

    @BeforeEach
    void setUp() {
        this.petDataUtil.cleanUp();
        this.userDataUtil.cleanUp();

        dog = this.petDataUtil.createDog(
                TEST_DOG_NAME,
                this.userDataUtil.createTestAdmin(TEST_ADMIN_EMAIL));
        cat = this.petDataUtil.createCat(
                TEST_CAT_NAME,
                this.userDataUtil.createTestUser(TEST_USER_EMAIL));
    }

    @AfterEach
    void tearDown() {
        this.petDataUtil.cleanUp();
        this.userDataUtil.cleanUp();
    }

    @Test
    void testAddDog() {
        PetCreatingDto dogo = new PetCreatingDto();
        dogo.setName("Dogo")
                .setBreed("Husky")
                .setAge("14")
                .setDescription("Kucho")
                .setGender("MALE")
                .setImage("sdfgsdfgsdffs")
                .setWeight("UP_25KG");
        assertEquals(2, this.petService.getAllPets().size(), "Test before adding the new pet!");

        petService.addDog(dogo, TEST_USER_EMAIL);
        assertEquals(3, this.petService.getAllPets().size(), "Test after adding the new pet!");
    }

    @Test
    void testGetPetsDetails() {
        PetDetailsDto catDetails = this.petService.getPetDetails(cat.getId(), TEST_USER_EMAIL);
        assertEquals(catDetails.getName(), cat.getName(), "Names check!");
        assertEquals(catDetails.getBreed(), cat.getBreed(), "Breed check!");

        PetDetailsDto dogDetails = this.petService.getPetDetails(dog.getId(), TEST_ADMIN_EMAIL);
        assertEquals(dogDetails.getName(), dog.getName(), "Names check!");
        assertEquals(dogDetails.getBreed(), dog.getBreed(), "Breed check!");
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
        List<PetViewModelDto> allPetsForUser = this.petService.getAllMyPets(TEST_USER_EMAIL);
        assertEquals(1, allPetsForUser.size(), "Check the count of all pets!");

        List<PetViewModelDto> allPetsForUser1 = this.petService.getAllMyPets(TEST_USER1_EMAIL);
        assertEquals(0, allPetsForUser1.size(), "Check the count of all pets!");

        List<PetViewModelDto> allPetsForAdmin = this.petService.getAllMyPets(TEST_ADMIN_EMAIL);
        assertEquals(1, allPetsForAdmin.size(), "Check the count of all pets!");
    }
}