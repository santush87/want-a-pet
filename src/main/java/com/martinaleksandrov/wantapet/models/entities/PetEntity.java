package com.martinaleksandrov.wantapet.models.entities;

import com.martinaleksandrov.wantapet.models.enums.GenderEnum;
import com.martinaleksandrov.wantapet.models.enums.PetType;
import com.martinaleksandrov.wantapet.models.enums.WeightRange;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "pets")
@Getter
@Setter
@SuperBuilder
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
}
