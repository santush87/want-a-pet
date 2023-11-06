package com.martinaleksandrov.wantapet.init;

import com.martinaleksandrov.wantapet.models.entities.PetTypeEntity;
import com.martinaleksandrov.wantapet.models.enums.PetType;
import com.martinaleksandrov.wantapet.reporitories.PetTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class PetInit implements CommandLineRunner {

    private final PetTypeRepository petTypeRepository;

    @Override
    public void run(String... args) throws Exception {
        boolean hasPetTypes = this.petTypeRepository.count() > 0;

        if (!hasPetTypes) {
            Set<PetTypeEntity> pets = new HashSet<>();

            Arrays.stream(PetType.values())
                    .forEach(petType -> {
                        PetTypeEntity type = new PetTypeEntity(petType);
                        pets.add(type);
                    });
            this.petTypeRepository.saveAll(pets);
        }
    }
}
