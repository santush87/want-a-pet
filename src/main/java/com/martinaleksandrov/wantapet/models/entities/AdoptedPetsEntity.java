package com.martinaleksandrov.wantapet.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "adopted_pets")
@Getter
@Setter
@SuperBuilder
public class AdoptedPetsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String petsName;

    @Column
    private String petsBreed;

    @Column
    private String petsImage;

    @Column
    @PositiveOrZero
    private int age;

    @ManyToOne
    private UserEntity newOwner;

    @ManyToOne
    private UserEntity prevOwner;

    @Column
    private LocalDate adoptionDate;

    @Column
    private boolean completeAdoption;

    @Override
    public String toString() {
        return "Pet's name is '" + petsName + '\'' +
                ", breed - '" + petsBreed + '\'' +
                ", age - " + age +
                ", adopted on " + adoptionDate.toString();
    }

    public AdoptedPetsEntity() {
        this.completeAdoption = false;
    }
}
