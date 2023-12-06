package com.martinaleksandrov.wantapet.models.entities;

import com.martinaleksandrov.wantapet.models.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Table(name = "roles")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class UserRoleEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public UserRoleEntity(RoleEnum role) {
        this.role = role;
    }
}
