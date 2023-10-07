package com.lms.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.userservice.entity.User;
import com.lms.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testRegisterUser() throws Exception {
        User user = new User();
        user.setEmailId("name@gmail.com");
        user.setName("Name");
        user.setPassword("Password");
        user.setIsAdmin(false);
        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1.0/lms/company/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void testRegisterUserException() throws Exception {
        when(userService.save(Mockito.<User>any())).thenThrow(new NullPointerException());
        User user = new User();
        user.setEmailId("name@gmail.com");
        user.setName("Name");
        user.setPassword("Password");
        user.setIsAdmin(false);
        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1.0/lms/company/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void testLoginUser() throws Exception {
        User user = new User();
        user.setEmailId("name@gmail.com");
        user.setName("Name");
        user.setPassword("Password");
        user.setIsAdmin(false);

        when(userService.login(Mockito.<User>any())).thenReturn(user);

        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1.0/lms/company/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void testLoginUserNull() throws Exception {
        User user = new User();
        user.setEmailId("name@gmail.com");
        user.setName("Name");
        user.setPassword("Password");
        user.setIsAdmin(false);

        when(userService.login(Mockito.<User>any())).thenReturn(null);

        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1.0/lms/company/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void testLoginUserException() throws Exception {
        User user = new User();
        user.setEmailId("name@gmail.com");
        user.setName("Name");
        user.setPassword("Password");
        user.setIsAdmin(false);

        when(userService.login(Mockito.<User>any())).thenThrow(new NullPointerException());

        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1.0/lms/company/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(200));
    }
}
