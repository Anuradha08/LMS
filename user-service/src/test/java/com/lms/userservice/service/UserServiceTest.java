package com.lms.userservice.service;

import com.lms.userservice.entity.User;
import com.lms.userservice.repository.UserRespository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserRespository userRespository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveUser() {
        User user = new User();
        user.setEmailId("42");
        user.setName("Name");
        user.setPassword("Password");
        user.setIsAdmin(true);
        when(userRespository.save(Mockito.<User>any())).thenReturn(user);
        assertEquals(user, userService.save(user));
        verify(userRespository).save(Mockito.<User>any());
    }

    @Test
    void testLoginUser() {
        User user = new User();
        user.setEmailId("42");
        user.setName("Name");
        user.setPassword("Password");
        user.setIsAdmin(true);
        when(userRespository.findByNameAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(user);
        assertEquals(user, userService.login(user));
        verify(userRespository).findByNameAndPassword(Mockito.anyString(), Mockito.anyString());
    }
}
