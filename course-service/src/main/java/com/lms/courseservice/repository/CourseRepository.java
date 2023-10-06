package com.lms.courseservice.repository;

import com.lms.courseservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {

    List<Course> findByTechnology(String technology);

    @Query(value = "select * from course c where c.duration_from >= :durationFrom and c.duration_to <= :durationTo and c.technology = :technology", nativeQuery = true)
    List<Course> filterRecords(@Param("technology") String technology, @Param("durationFrom") Date durationFrom, @Param("durationTo") Date durationTo);

    @Transactional
    long deleteById(int id);
}
