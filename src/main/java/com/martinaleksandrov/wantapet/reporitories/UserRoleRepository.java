package com.martinaleksandrov.wantapet.reporitories;

import com.martinaleksandrov.wantapet.models.entities.UserRoleEntity;
import com.martinaleksandrov.wantapet.models.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity findByRole(RoleEnum role);

    List<UserRoleEntity> findAllByRoleIn(List<RoleEnum> roles);
}
