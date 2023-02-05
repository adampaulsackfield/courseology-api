package com.courseology.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesRepository extends JpaRepository<Course, Long> {
    List<Course> getCoursesByCategory(String category);
}
