package com.lms.courseservice.service;

import com.lms.courseservice.entity.Course;
import com.lms.courseservice.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class CourseServiceTest {
    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddCourse() throws ParseException {
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
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course);
        assertEquals(course, courseService.save(course));
        verify(courseRepository).save(Mockito.<Course>any());
    }

    @Test
    void testDeleteCourse() {
        when(courseRepository.deleteById(Mockito.anyInt())).thenReturn(1L);
        assertEquals(1L, courseService.deleteCourse(1));
        verify(courseRepository).deleteById(Mockito.anyInt());
    }

    @Test
    void testGetAllCourses() {
        ArrayList<Course> courseList = new ArrayList<>();
        when(courseRepository.findAll()).thenReturn(courseList);
        List<Course> actualAllCourses = courseService.getAllCourses();
        assertSame(courseList, actualAllCourses);
        assertTrue(actualAllCourses.isEmpty());
        verify(courseRepository).findAll();
    }

    @Test
    void testGetCourseByTechnology() {
        ArrayList<Course> courseList = new ArrayList<>();
        when(courseRepository.findByTechnology(Mockito.<String>any())).thenReturn(courseList);
        List<Course> actualCourseByTechnology = courseService.getCoursesByTechnology("Technology");
        assertSame(courseList, actualCourseByTechnology);
        assertTrue(actualCourseByTechnology.isEmpty());
        verify(courseRepository).findByTechnology(Mockito.<String>any());
    }

    @Test
    void testGetCourseByTechnologyOrDuration() throws ParseException {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom = dateFormat.parse("2023-01-01");
        Date dateTo = dateFormat.parse("2023-12-31");

        ArrayList<Course> courseList = new ArrayList<>();
        when(courseRepository.filterRecords(Mockito.anyString(), Mockito.any(), Mockito.any()))
                .thenReturn(courseList);
        List<Course> actualCourseByTechnologyOrDuration = courseService.filterRecords("Technology", dateFrom, dateTo);
        assertSame(courseList, actualCourseByTechnologyOrDuration);
        assertTrue(actualCourseByTechnologyOrDuration.isEmpty());
        verify(courseRepository).filterRecords(Mockito.anyString(), Mockito.any(), Mockito.any());
    }
}
