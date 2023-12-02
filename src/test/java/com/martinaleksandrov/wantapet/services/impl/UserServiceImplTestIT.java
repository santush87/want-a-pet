package com.martinaleksandrov.wantapet.services.impl;

import com.martinaleksandrov.wantapet.models.dtos.UserDetailsDto;
import com.martinaleksandrov.wantapet.models.dtos.UserEditDto;
import com.martinaleksandrov.wantapet.models.dtos.UserRegisterDto;
import com.martinaleksandrov.wantapet.models.entities.UserEntity;
import com.martinaleksandrov.wantapet.reporitories.UserRepository;
import com.martinaleksandrov.wantapet.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTestIT {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    private UserRegisterDto userDto = new UserRegisterDto();

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();

        userDto.setEmail("email@email.com")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setUserType("ORGANIZATION")
                .setPhoneNumber("0123456789")
                .setCountry("BULGARIA")
                .setCity("Sofia")
                .setStreet("ulica")
                .setStreetNumber("5");
        userDto.setPassword("test1234")
                .setConfirmPassword("test1234");

        this.userService.register(userDto);

    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
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

        assertTrue(this.userService.register(userTestDto));
        assertFalse(this.userService.register(userTestDto));
    }


    @Test
    void testIsTheUserAnAdmin() {

        Optional<UserEntity> testAdmin = this.userService.findByEmail(userDto.getEmail());

        assertTrue(this.userService.isAdmin(testAdmin.get()));

    }

    @Test
    void testGetAllUsers() {
        List<UserRegisterDto> all = this.userService.getAll();

        assertEquals(1, all.size());
    }

    @Test
    void testGetUserDetailsThrowEx() {
        assertThrows(NoSuchElementException.class,
                () -> this.userService.getUserDetails("asdsasd"));
    }

    @Test
    void testGetUserDetailsSuccess(){
        UserDetailsDto userDetails = this.userService.getUserDetails("email@email.com");

        assertEquals("email@email.com", userDetails.getEmail());
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

        Optional<UserEntity> user = this.userService.findByEmail(userDto.getEmail());

        assertFalse(this.userService.editUser("asdasd", userEdit));
        assertTrue(this.userService.editUser(user.get().getId(), userEdit));
    }

}