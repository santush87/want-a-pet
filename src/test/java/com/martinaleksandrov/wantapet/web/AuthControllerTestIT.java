package com.martinaleksandrov.wantapet.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegistration() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/register")
                        .param("firstName", "Martin")
                        .param("lastName", "Aleksandrov")
                        .param("email", "martin@abv.bg")
                        .param("password", "asdasdasd")
                        .param("confirmPassword", "asdasdasd")
                        .param("phoneNumber", "0877123456" )
                        .param("country", "BULGARIA")
                        .param("city", "Sofia")
                        .param("street", "Ivan Vazov")
                        .param("streetNumber", "25")
                        .param("userType", "PRIVATE")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection());
    }
}