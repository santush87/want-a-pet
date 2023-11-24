package com.martinaleksandrov.wantapet.models.entities;

import com.martinaleksandrov.wantapet.models.enums.CountryEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
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
    private String streetNumber;

    public UserAddress setCountryEnum(CountryEnum countryEnum) {
        this.countryEnum = countryEnum;
        return this;
    }

    public UserAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public UserAddress setStreet(String street) {
        this.street = street;
        return this;
    }

    public UserAddress setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }
}
