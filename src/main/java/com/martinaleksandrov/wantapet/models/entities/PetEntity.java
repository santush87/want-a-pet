package com.martinaleksandrov.wantapet.models.entities;

import com.martinaleksandrov.wantapet.models.enums.WeightRange;
import com.martinaleksandrov.wantapet.models.enums.GenderEnum;
import com.martinaleksandrov.wantapet.models.enums.PetType;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pets")
@Setter
@Getter
@NoArgsConstructor
public class PetEntity extends BaseEntity{

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
    private GenderEnum gender;

    @Column(nullable = false)
    private String image;

    @Column
    @PositiveOrZero
    private int age;

    @Column(columnDefinition = "TEXT")
    private String description;

}
