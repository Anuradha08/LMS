package com.lms.courseservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.courseservice.entity.Course;
import com.lms.courseservice.service.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.when;

public class CourseControllerTest {
    @InjectMocks
    private CourseController courseController;

    @Mock
    private CourseService courseService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }

    @Test
    void testAddCourse() throws Exception {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom = dateFormat.parse("2023-01-01");
        Date dateTo = dateFormat.parse("2023-12-31");
        Course course = new Course();
        course.setCourseName("Course Name");
        course.setDescription("The characteristics of someone or something");
        course.setDurationFrom(dateFrom);
        course.setDurationTo(dateTo);
        course.setId(1);
        course.setLaunchURL("https://example.org/example");
        course.setTechnology("Technology");
        String content = (new ObjectMapper()).writeValueAsString(course);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1.0/lms/courses/add")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    void testDeleteCourse() throws Exception {
        when(courseService.deleteCourse(Mockito.anyInt())).thenReturn(new Long(1));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1.0/lms/courses/delete/{id}","1");
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    @Test
    void testGetAllCourses() throws Exception {
        when(courseService.getAllCourses()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1.0/lms/courses/getall");
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllCourses2() throws Exception {
        when(courseService.getAllCourses()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1.0/lms/courses/getall");
        requestBuilder.characterEncoding("Encoding");
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetCourseByTechnology() throws Exception {
        when(courseService.getCoursesByTechnology(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1.0/lms/courses/info/{technology}", "Technology");
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetCourseByTechnology2() throws Exception {
        when(courseService.getCoursesByTechnology(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1.0/lms/courses/info/{technology}", "Technology");
        requestBuilder.characterEncoding("Encoding");
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetCourseByTechnologyAndDuration() throws Exception {
        when(courseService.filterRecords(Mockito.anyString(),Mockito.any(),Mockito.any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1.0/lms/courses/get")
                .contentType(MediaType.APPLICATION_JSON)
                .param("technology", "Technology")
                .param("durationFrom","2023-01-01")
                .param("durationTo","2023-12-31");

        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

}
