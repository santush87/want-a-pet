package com.martinaleksandrov.wantapet.testUtils;

import com.martinaleksandrov.wantapet.models.dtos.binding.PetCreatingDto;
import com.martinaleksandrov.wantapet.models.entities.PetEntity;
import com.martinaleksandrov.wantapet.models.entities.UserEntity;
import com.martinaleksandrov.wantapet.models.enums.PetType;
import com.martinaleksandrov.wantapet.reporitories.PetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestPetDataUtil {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private ModelMapper modelMapper;

    private PetEntity createPet(String petsName, UserEntity user, PetType petType) {
        PetCreatingDto petDto = new PetCreatingDto()
                .setName(petsName)
                .setGender("MALE")
                .setAge("2")
                .setImage("sdfsdfsdf")
                .setBreed("poroda")
                .setWeight("BETWEEN_6KG_AND_10KG")
                .setDescription("jivotno");

        PetEntity petEntity = this.modelMapper.map(petDto, PetEntity.class);
        petEntity.setOwner(user);
        petEntity.setType(petType);

        this.petRepository.save(petEntity);
        return petEntity;
    }

    public PetEntity createDog(String petsName, UserEntity user) {
        return createPet(petsName, user, PetType.DOG);
    }

    public PetEntity createCat(String petsName, UserEntity user) {
        return createPet(petsName, user, PetType.CAT);
    }

    public void cleanUp() {
        this.petRepository.deleteAll();
    }
}
