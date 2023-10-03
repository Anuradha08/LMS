package com.lms.courseservice.service;

import com.lms.courseservice.entity.Course;
import com.lms.courseservice.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public long deleteCourse(String courseId) {
        return courseRepository.deleteByCourseName(courseId);
    }

    public List<Course> getCoursesByTechnology(String technology) {
        return courseRepository.findByTechnology(technology);
    }

    public List<Course> getCourseByTechnologyOrDuration(String technology, Date startDate, Date endDate) {
        return courseRepository.findByTechnologyOrDurationBetween(technology, startDate, endDate);
    }

}
