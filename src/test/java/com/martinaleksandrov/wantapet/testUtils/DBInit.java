package com.martinaleksandrov.wantapet.testUtils;

import com.martinaleksandrov.wantapet.models.entities.UserRoleEntity;
import com.martinaleksandrov.wantapet.models.enums.RoleEnum;
import com.martinaleksandrov.wantapet.reporitories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DBInit implements CommandLineRunner {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void run(String... args) throws Exception {
        Set<UserRoleEntity> userRoles = new HashSet<>();

        Arrays.stream(RoleEnum.values())
                .forEach(role -> {
                    UserRoleEntity userRole = new UserRoleEntity(role);

                    userRoles.add(userRole);
                });
        this.userRoleRepository.saveAll(userRoles);
    }
}
