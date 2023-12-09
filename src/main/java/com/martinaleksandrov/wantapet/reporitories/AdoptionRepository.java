package com.martinaleksandrov.wantapet.reporitories;

import com.martinaleksandrov.wantapet.models.entities.AdoptedPetsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionRepository extends JpaRepository<AdoptedPetsEntity, Long> {

    List<AdoptedPetsEntity> findAllByNewOwnerId(String id);
    List<AdoptedPetsEntity> findAllByPrevOwnerId(String id);

    List<AdoptedPetsEntity> findAllBy();
}
