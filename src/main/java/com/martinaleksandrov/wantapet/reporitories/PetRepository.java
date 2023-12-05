package com.martinaleksandrov.wantapet.reporitories;

import com.martinaleksandrov.wantapet.models.entities.PetEntity;
import com.martinaleksandrov.wantapet.models.enums.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long> {

    List<PetEntity> findAllByType(PetType petType);
    List<PetEntity> findAllByOwnerId(String id);

    PetEntity findByName(String name);
}
