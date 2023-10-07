package com.lms.courseservice.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class CourseTest {

    @Test
    void testConstructor() throws ParseException {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom = dateFormat.parse("2023-01-01");
        Date dateTo = dateFormat.parse("2023-12-31");
        Course actualCourse = new Course();
        actualCourse.setCourseName("Course Name");
        actualCourse.setDescription("The characteristics of someone or something");
        actualCourse.setDurationFrom(dateFrom);
        actualCourse.setDurationTo(dateTo);
        actualCourse.setId(1);
        actualCourse.setLaunchURL("https://example.org/example");
        actualCourse.setTechnology("Technology");
        assertEquals("Course Name", actualCourse.getCourseName());
        assertEquals("The characteristics of someone or something", actualCourse.getDescription());
        assertEquals(dateFrom, actualCourse.getDurationFrom());
        assertEquals(dateTo, actualCourse.getDurationTo());
        assertEquals(1, actualCourse.getId());
        assertEquals("https://example.org/example", actualCourse.getLaunchURL());
        assertEquals("Technology", actualCourse.getTechnology());
    }
}
