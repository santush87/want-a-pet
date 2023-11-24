package com.martinaleksandrov.wantapet.models.entities;

import com.martinaleksandrov.wantapet.models.enums.GenderEnum;
import com.martinaleksandrov.wantapet.models.enums.PetType;
import com.martinaleksandrov.wantapet.models.enums.WeightRange;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pets")
@Getter
@NoArgsConstructor
public class PetEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PetType type;

    @Column
    private String name;

    @Column
    private String breed;

    @Column
    @Enumerated(EnumType.STRING)
    private WeightRange weight;

    @Column
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Column(nullable = false)
    private String image;

    @Column
    @PositiveOrZero
    private int age;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private UserEntity owner;

    public PetEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public PetEntity setType(PetType type) {
        this.type = type;
        return this;
    }

    public PetEntity setName(String name) {
        this.name = name;
        return this;
    }

    public PetEntity setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public PetEntity setWeight(WeightRange weight) {
        this.weight = weight;
        return this;
    }

    public PetEntity setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public PetEntity setImage(String image) {
        this.image = image;
        return this;
    }

    public PetEntity setAge(int age) {
        this.age = age;
        return this;
    }

    public PetEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public PetEntity setOwner(UserEntity owner) {
        this.owner = owner;
        return this;
    }
}
