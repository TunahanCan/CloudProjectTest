package com.example.authservice;

import com.example.authservice.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.util.Assert;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AuthServiceApplicationTests {

    @Autowired
    UserService userService;


    @DisplayName("Test Service entegration")
    @Test
    void testGet(){
        assertEquals("Hello JUnit 5", userService.helooUserService());
    }



}
