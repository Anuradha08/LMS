package com.lms.courseservice.controller;

import com.lms.courseservice.entity.Course;
import com.lms.courseservice.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/lms/courses")
public class CourseController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    CourseService courseService;

    @GetMapping("/getall")
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> courses = new ArrayList<>();
        logger.info("Inside getAllCourses");
        try{
            courses = courseService.getAllCourses();
            logger.info("List of courses: {}", courses);
        }catch (Exception e){
            logger.info("Unable to get list of courses {}", e.getMessage());
        }

        return ResponseEntity.ok(courses);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Course> addCourse(@Validated @RequestBody Course course){
        Course response = new Course();
        logger.info("Inside add course");
        try {
            response = courseService.save(course);
            logger.info("{} added successfully", response);
        }
        catch(Exception e){
            logger.info("Unable to add course {}", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Long> deleteCourse(@PathVariable("id") int id){
        Long courseRes = null;
        try{
            courseRes = courseService.deleteCourse(id);
            logger.info("{} deleted successfully",courseRes);
        }
        catch (Exception e){
            logger.info("Unable to delete Course {}", e.getMessage());
        }
        return ResponseEntity.ok(courseRes);
    }

    @GetMapping(path = "/get")
    public ResponseEntity<List<Course>> getCourseByDuration(@RequestParam String technology, @RequestParam String durationFrom, @RequestParam String durationTo){
        List<Course> courses = new ArrayList<>();
        try {
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
            Date dateFrom = dateFormat.parse(durationFrom);
            Date dateTo = dateFormat.parse(durationTo);
            courses = courseService.filterRecords(technology, dateFrom, dateTo);
            logger.info("Courses fetched between duration {} and {} are {}", durationFrom, durationTo, courses);
        }
        catch (Exception e)
        {
            logger.info("Unable to get courses by duration {}", e.getMessage());
        }
        return ResponseEntity.ok(courses);
    }

    @GetMapping(path = "/info/{technology}")
    public ResponseEntity<List<Course>> getCoursesByTechnology(@PathVariable("technology") String technology){
        List<Course> response = new ArrayList<>();
        try {
            response = courseService.getCoursesByTechnology(technology);
            logger.info("Courses fetched by technology {} are {}",technology,response);
        }
        catch(Exception e){
            logger.info("Unable to get courses by technology {}", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}
