package com.martinaleksandrov.wantapet.models.entities;

import com.martinaleksandrov.wantapet.models.enums.CountryEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Setter
@Getter
@NoArgsConstructor
public class UserAddress extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CountryEnum countryEnum;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;
    @Column
    private int number;
}
