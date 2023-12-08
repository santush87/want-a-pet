package com.martinaleksandrov.wantapet.services.impl;

import com.martinaleksandrov.wantapet.models.dtos.view.AdoptedPetsViewDto;
import com.martinaleksandrov.wantapet.models.entities.PetEntity;
import com.martinaleksandrov.wantapet.models.entities.UserEntity;
import com.martinaleksandrov.wantapet.reporitories.AdoptionRepository;
import com.martinaleksandrov.wantapet.services.AdoptedPetsService;
import com.martinaleksandrov.wantapet.testUtils.TestPetDataUtil;
import com.martinaleksandrov.wantapet.testUtils.TestUserDataUtil;
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
    private AdoptionRepository adoptionRepository;
    @Autowired
    private AdoptedPetsService adoptedPetsService;
    @Autowired
    private TestUserDataUtil userDataUtil;
    @Autowired
    private TestPetDataUtil petDataUtil;
    private static final String NEW_OWNER_EMAIL = "new@example.com";
    private static final String PREV_OWNER_EMAIL = "old@example.com";
    private static final String TEST_CAT_NAME = "Maca";
    private PetEntity cat = new PetEntity();

    @BeforeEach
    void setUp(){
        this.adoptionRepository.deleteAll();
        this.petDataUtil.cleanUp();
        this.userDataUtil.cleanUp();

        UserEntity prevOwner = this.userDataUtil.createTestUser(PREV_OWNER_EMAIL);
        this.userDataUtil.createTestUser(NEW_OWNER_EMAIL);

        cat = this.petDataUtil.createCat(
                TEST_CAT_NAME,
                prevOwner);
    }

    @AfterEach
    void tearDown() {
        this.adoptionRepository.deleteAll();
        this.petDataUtil.cleanUp();
        this.userDataUtil.cleanUp();
    }

    @Test
    void testAdoptPet(){
        assertEquals(0, this.adoptionRepository.count());

        this.adoptedPetsService.adoptPet(cat.getId(), NEW_OWNER_EMAIL);
        assertEquals(1, this.adoptionRepository.count());

    }

    @Test
    void testGetAllMyAdoptedPets(){
        this.adoptedPetsService.adoptPet(cat.getId(), NEW_OWNER_EMAIL);

        List<AdoptedPetsViewDto> petsNewOwner = this.adoptedPetsService.getAllMyAdoptedPets(NEW_OWNER_EMAIL);
        List<AdoptedPetsViewDto> petsPrevOwner = this.adoptedPetsService.getAllMyAdoptedPets(PREV_OWNER_EMAIL);

        assertEquals(0, petsPrevOwner.size(), "Testing that the previous owner does not have any adopted pets!");
        assertEquals(1, petsNewOwner.size(), "The new owner should have 1 adopted pet!");
    }

    @Test
    void testFindAllAdoptedPets(){

        assertEquals(0, this.adoptedPetsService.findAllAdoptedPets().size(), "Testing before any pet were adopted!");

        this.adoptedPetsService.adoptPet(cat.getId(), NEW_OWNER_EMAIL);
        assertEquals(1, this.adoptedPetsService.findAllAdoptedPets().size(), "Testing after a pet was adopted!");
    }
}