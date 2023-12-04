package com.martinaleksandrov.wantapet.testUtils;

import com.martinaleksandrov.wantapet.models.dtos.PetCreatingDto;
import com.martinaleksandrov.wantapet.reporitories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestPetDataUtil {

    @Autowired
    private PetRepository petRepository;

    public PetCreatingDto createPet(String name) {
        PetCreatingDto pet = new PetCreatingDto()
                .setName(name)
                .setGender("MALE")
                .setAge("2")
                .setImage("sdfsdfsdf")
                .setBreed("poroda")
                .setWeight("BETWEEN_6KG_AND_10KG")
                .setDescription("jivotno");

        return pet;
    }

    public void cleanUp() {
        this.petRepository.deleteAll();
    }
}
