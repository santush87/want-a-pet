package com.martinaleksandrov.wantapet.init;

import com.martinaleksandrov.wantapet.models.entities.UserRoleEntity;
import com.martinaleksandrov.wantapet.models.enums.RoleEnum;
import com.martinaleksandrov.wantapet.reporitories.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class RolesInit implements CommandLineRunner {

    private final UserRoleRepository userRoleRepository;

    @Override
    public void run(String... args) throws Exception {
//        boolean hasRoles = this.userRoleRepository.count() > 0;
//
//        if (!hasRoles) {
//            Set<UserRoleEntity> userRoles = new HashSet<>();
//
//            Arrays.stream(RoleEnum.values())
//                    .forEach(role -> {
//                        UserRoleEntity userRole = new UserRoleEntity(role);
//
//                        userRoles.add(userRole);
//                    });
//            this.userRoleRepository.saveAll(userRoles);
//        }
    }
}
