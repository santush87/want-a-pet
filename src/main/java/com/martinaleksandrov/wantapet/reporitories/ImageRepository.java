package com.martinaleksandrov.wantapet.reporitories;

import com.martinaleksandrov.wantapet.models.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
