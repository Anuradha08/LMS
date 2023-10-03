package com.lms.courseservice.repository;

import com.lms.courseservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {

    List<Course> findByTechnology(String technology);

    List<Course> findByTechnologyOrDurationBetween(String technology, Date durationFrom, Date durationTo);

    @Transactional
    long deleteByCourseName(String courseName);
}
