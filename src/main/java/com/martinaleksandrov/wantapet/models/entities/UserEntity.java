package com.martinaleksandrov.wantapet.models.entities;

import com.martinaleksandrov.wantapet.models.enums.CountryEnum;
import com.martinaleksandrov.wantapet.models.enums.RoleEnum;
import com.martinaleksandrov.wantapet.models.enums.TypeOfUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
public class UserEntity extends BaseEntity{

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeOfUser type;

    @Column(nullable = false)
    private String phoneNumber;

//    @OneToOne
//    private UserAddress address;

    @Column
    @CurrentTimestamp
    private LocalDate createdOn;

    @ManyToMany()
    private List<PetEntity> pets;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CountryEnum countryEnum;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;
    @Column
    private int number;

    @Column
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public UserEntity() {
        this.pets = new ArrayList<>();
        this.createdOn = LocalDate.now();
        this.role = RoleEnum.USER;
    }
}
