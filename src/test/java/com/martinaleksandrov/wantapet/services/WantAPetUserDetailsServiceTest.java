package com.martinaleksandrov.wantapet.services;


import com.martinaleksandrov.wantapet.models.entities.UserEntity;
import com.martinaleksandrov.wantapet.models.entities.UserRoleEntity;
import com.martinaleksandrov.wantapet.models.enums.RoleEnum;
import com.martinaleksandrov.wantapet.models.enums.TypeOfUser;
import com.martinaleksandrov.wantapet.reporitories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class WantAPetUserDetailsServiceTest {

    private WantAPetUserDetailsService serviceToTest;
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new WantAPetUserDetailsService(mockUserRepository);
    }

    @Test
    void testUserNotFound() {
        assertThrows(UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("martin@abv.bg"));
    }

    @Test
    void testUserFoundException() {
        // ARRANGE
        UserEntity testUserEntity = createTestUser();
        when(mockUserRepository.findByEmail(testUserEntity.getEmail()))
                .thenReturn(Optional.of(testUserEntity));

        // ACT
        UserDetails userDetails =
                serviceToTest.loadUserByUsername(testUserEntity.getEmail());

        // ASSERT
        assertNotNull(userDetails);
        assertEquals(testUserEntity.getEmail(),
                userDetails.getUsername(), "Username is not mapped to email!");
        assertEquals(testUserEntity.getPassword(),
                userDetails.getPassword(), "Passwords should match!");
        assertEquals(2, userDetails.getAuthorities().size());
        assertTrue(containsAuthority(userDetails,
                        "ROLE_" + RoleEnum.ADMIN),
                "The user is not ADMIN!");
        assertTrue(containsAuthority(userDetails,
                        "ROLE_" + RoleEnum.USER),
                "The user is not USER!");
    }

    private boolean containsAuthority(UserDetails userDetails,
                                      String expectedAuthority) {
        return userDetails
                .getAuthorities()
                .stream()
                .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
    }

    private static UserEntity createTestUser() {
        UserEntity user = new UserEntity();

        user.setFirstName("Martin");
        user.setLastName("Aleksandrov");
        user.setUserType(TypeOfUser.PRIVATE);
        user.setPhoneNumber("123456789");
        user.setEmail("martin@abv.bg");
        user.setRoles(List.of(
                new UserRoleEntity(RoleEnum.USER),
                new UserRoleEntity(RoleEnum.ADMIN)
        ));
        user.setPassword("asdasdasd");

        return user;
    }
}
