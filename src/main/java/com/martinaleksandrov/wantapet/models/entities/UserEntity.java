package com.martinaleksandrov.wantapet.models.entities;

import com.martinaleksandrov.wantapet.models.enums.TypeOfUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@AllArgsConstructor
@Builder
public class UserEntity extends BaseEntity {

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
    private TypeOfUser userType;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToOne
    private UserAddress address;

    @Column
    @CurrentTimestamp
    private LocalDate createdOn;

    @OneToMany()
    private List<PetEntity> pets;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<UserRoleEntity> roles = new ArrayList<>();

    public UserEntity() {
        this.pets = new ArrayList<>();
        this.createdOn = LocalDate.now();
//        this.roles = new ArrayList<>().add(UserRoleEntity.);
    }
}
