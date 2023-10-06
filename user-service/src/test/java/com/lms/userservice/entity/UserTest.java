package com.lms.userservice.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    void testConstructor() {
        User actualUser = new User();
        actualUser.setEmailId("42");
        actualUser.setName("Name");
        actualUser.setPassword("Password");
        assertEquals("42", actualUser.getEmailId());
        assertEquals("Name", actualUser.getName());
        assertEquals("Password", actualUser.getPassword());
    }
}
