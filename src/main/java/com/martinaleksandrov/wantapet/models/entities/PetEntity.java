package com.martinaleksandrov.wantapet.models.entities;

import com.martinaleksandrov.wantapet.models.enums.PetType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "pets")
@Setter
@Getter
@NoArgsConstructor
public class PetEntity extends BaseEntity{

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PetType type;

    @Column()
    private String breed;

    @Column(nullable = false)
    private String image;

    @Column
    @Past
    private LocalDate birthOn;

    @Column(columnDefinition = "TEXT")
    private String description;

}
