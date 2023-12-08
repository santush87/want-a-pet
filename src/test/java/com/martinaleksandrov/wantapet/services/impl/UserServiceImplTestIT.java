package com.martinaleksandrov.wantapet.services.impl;

import com.martinaleksandrov.wantapet.exceptions.UserNotFoundException;
import com.martinaleksandrov.wantapet.models.dtos.binding.UserRegisterDto;
import com.martinaleksandrov.wantapet.models.dtos.view.UserDetailsDto;
import com.martinaleksandrov.wantapet.models.dtos.view.UserEditDto;
import com.martinaleksandrov.wantapet.models.entities.UserEntity;
import com.martinaleksandrov.wantapet.reporitories.UserRepository;
import com.martinaleksandrov.wantapet.services.UserService;
import com.martinaleksandrov.wantapet.testUtils.TestUserDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTestIT {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestUserDataUtil testUserDataUtil;

    private static final String TEST_USER_EMAIL = "user@example.com";
    private static final String TEST_USER1_EMAIL = "user1@example.com";
    private static final String TEST_ADMIN_EMAIL = "admin@example.com";


    @BeforeEach
    void setUp() {
        this.userRepository.deleteAll();
        this.testUserDataUtil.cleanUp();

        testUserDataUtil.createTestUser(TEST_USER_EMAIL);
        testUserDataUtil.createTestAdmin(TEST_ADMIN_EMAIL);

    }

    @AfterEach
    void tearDown() {
        this.userRepository.deleteAll();
        this.testUserDataUtil.cleanUp();
    }

    @Test
    void testRegisterSuccessful() {
        UserRegisterDto userTestDto = new UserRegisterDto();

        userTestDto.setEmail("email2@email.com")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setUserType("ORGANIZATION")
                .setPhoneNumber("0123456789")
                .setCountry("BULGARIA")
                .setCity("Sofia")
                .setStreet("ulica")
                .setStreetNumber("5");
        userTestDto.setPassword("test1234")
                .setConfirmPassword("test1234");

        // First time is successful
        assertTrue(this.userService.register(userTestDto));
        // Second time is not successful because user already exists
        assertFalse(this.userService.register(userTestDto));
    }


    @Test
    void testIsTheUserAnAdmin() {
        UserEntity testAdmin = this.userService.findByEmail(TEST_ADMIN_EMAIL)
                .orElseThrow(()->new UserNotFoundException("User not found!"));

        assertTrue(this.userService.isAdmin(testAdmin));

    }

    @Test
    void testGetAllUsers() {
        List<UserRegisterDto> all = this.userService.getAll();

        assertEquals(2, all.size(), "There are 2 users from the beginning!");

        testUserDataUtil.createTestUser(TEST_USER1_EMAIL);

        all = this.userService.getAll();
        assertEquals(3, all.size(), "Added 1 more user and now the count must be 3!");

    }

    @Test
    void testGetUserDetailsThrowEx() {
        assertThrows(UserNotFoundException.class,
                () -> this.userService.getUserDetails("asdsasd"));
    }

    @Test
    void testGetUserDetailsSuccess(){
        UserDetailsDto userDetails = this.userService.getUserDetails(TEST_USER_EMAIL);

        assertEquals("user@example.com", userDetails.getEmail());
    }

    @Test
    void testEditUser(){
        UserEditDto userEdit = new UserEditDto();
        userEdit.setFirstName("firstName1")
                .setLastName("lastName1")
                .setUserType("ORGANIZATION")
                .setPhoneNumber("011111111")
                .setCountry("BULGARIA")
                .setCity("Sofia")
                .setStreet("ulica2")
                .setStreetNumber("55");

        UserEntity user = this.userService.findByEmail(TEST_USER_EMAIL)
                .orElseThrow(()->new UserNotFoundException("User not found!"));

        assertFalse(this.userService.editUser("asdasd", userEdit));
        assertTrue(this.userService.editUser(user.getId(), userEdit));
    }

}