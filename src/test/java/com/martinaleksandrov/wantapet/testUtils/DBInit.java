package com.martinaleksandrov.wantapet.testUtils;

import com.martinaleksandrov.wantapet.models.entities.UserRoleEntity;
import com.martinaleksandrov.wantapet.models.enums.RoleEnum;
import com.martinaleksandrov.wantapet.reporitories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRoleRepository.count() == 0) {
            userRoleRepository.saveAll(List.of(
                    new UserRoleEntity().setRole(RoleEnum.USER),
                    new UserRoleEntity().setRole(RoleEnum.ADMIN)
            ));
        }
    }
}
