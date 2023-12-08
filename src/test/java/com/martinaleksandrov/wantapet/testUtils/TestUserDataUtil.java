package com.martinaleksandrov.wantapet.testUtils;

import com.martinaleksandrov.wantapet.models.entities.UserAddress;
import com.martinaleksandrov.wantapet.models.entities.UserEntity;
import com.martinaleksandrov.wantapet.models.entities.UserRoleEntity;
import com.martinaleksandrov.wantapet.models.enums.CountryEnum;
import com.martinaleksandrov.wantapet.models.enums.RoleEnum;
import com.martinaleksandrov.wantapet.models.enums.TypeOfUser;
import com.martinaleksandrov.wantapet.reporitories.AddressRepository;
import com.martinaleksandrov.wantapet.reporitories.UserRepository;
import com.martinaleksandrov.wantapet.reporitories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TestUserDataUtil {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private AddressRepository addressRepository;

    public UserEntity createTestUser(String email) {
        return createUser(email, List.of(RoleEnum.USER), createAddress("Hristo Botev"));
    }

    public UserEntity createTestAdmin(String email) {
        return createUser(email, List.of(RoleEnum.ADMIN), createAddress("Ivan Vazov"));
    }

    private UserEntity createUser(String email, List<RoleEnum> roles, UserAddress address) {

        List<UserRoleEntity> roleEntities = userRoleRepository.findAllByRoleIn(roles);

        UserEntity newUser = new UserEntity()
                .setEmail(email)
                .setFirstName("Test user first")
                .setLastName("Test user last")
                .setRoles(roleEntities)
                .setUserType(TypeOfUser.ORGANIZATION)
                .setCreatedOn(LocalDate.now())
                .setPhoneNumber("0123456789")
                .setPassword("asdasdasd")
                .setAddress(address);

        return userRepository.save(newUser);
    }

    private UserAddress createAddress(String street) {
        UserAddress address = new UserAddress()
                .setCountryEnum(CountryEnum.BULGARIA)
                .setCity("Sofia")
                .setStreet(street)
                .setStreetNumber("5");

        return this.addressRepository.save(address);
    }

    public void cleanUp() {
        this.userRepository.deleteAll();
        this.addressRepository.deleteAll();
    }
}
