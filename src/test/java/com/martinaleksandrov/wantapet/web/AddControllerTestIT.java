package com.martinaleksandrov.wantapet.web;

import com.martinaleksandrov.wantapet.models.entities.PetEntity;
import com.martinaleksandrov.wantapet.models.entities.UserEntity;
import com.martinaleksandrov.wantapet.testUtils.TestPetDataUtil;
import com.martinaleksandrov.wantapet.testUtils.TestUserDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AddControllerTestIT {

//    private static final String TEST_USER_EMAIL = "user@example.com";
//    private static final String TEST_ADMIN_EMAIL = "admin@example.com";
//
//
//    @Autowired
//    private TestPetDataUtil testPetDataUtil;
//
//    @Autowired
//    private TestUserDataUtil testUserDataUtil;

    @Autowired
    private MockMvc mockMvc;

//    @BeforeEach
//    void setUp() {
//        testPetDataUtil.cleanUp();
//        testUserDataUtil.cleanUp();
//    }
//
//    @AfterEach
//    void tearDown() {
//        testPetDataUtil.cleanUp();
//        testUserDataUtil.cleanUp();
//    }

    @Test
//    @WithMockUser(username = TEST_USER_EMAIL)
    void testAddDog() throws Exception {
//        UserEntity testUser = testUserDataUtil.createTestUser(TEST_USER_EMAIL);
//        PetEntity dog = testPetDataUtil.createDog("Ares", testUser);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/add/dog")
                        .param("name", "Ares")
                        .param("breed", "Husky")
                        .param("image", "http:///asdfsdfsd.jpeg")
                        .param("gender", "MALE")
                        .param("age", "5")
                        .param("weight", "UP_25KG" )
                        .param("description", "Super Dog")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection());
    }

    @Test
    void testAddCat() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/add/cat")
                        .param("name", "Mira")
                        .param("breed", "Angorka")
                        .param("image", "http:///asdfsdfsfgdfgd.jpeg")
                        .param("gender", "FEMALE")
                        .param("age", "12")
                        .param("weight", "BETWEEN_0KG_AND_5KG" )
                        .param("description", "Super cat")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection());
    }

}