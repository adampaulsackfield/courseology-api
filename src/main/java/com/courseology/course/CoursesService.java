package com.courseology.course;

import com.courseology.auth.AuthController;
import com.courseology.exception.CustomException;
import com.courseology.exception.NotFoundException;
import com.courseology.student.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoursesService {
    private CoursesRepository coursesRepository;

    @Autowired
    public CoursesService(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    // CREATE
    public void addCourse(Course course) {
        coursesRepository.save(course);
    }

    // READ
    public List<Course> getAllCourses(int limit) {
        return coursesRepository.findAll().stream().limit(limit).collect(Collectors.toList());
    }

    public List<Course> getCoursesByCategory(int limit, String category) {
        List<String> allowedCategories = new ArrayList<>(Arrays.asList(
                "coding",
                "languages"
        ));

        List<Course> courses = coursesRepository.getCoursesByCategory(category);

        List<Course> filteredCourses = courses.stream().limit(limit).collect(Collectors.toList());

        if(allowedCategories.contains(category)) {
            return filteredCourses;
        } else {
            throw new CustomException(HttpStatus.FORBIDDEN, "Invalid Category");
        }
    }

    public Course getCourseById(long id) {
        Optional<Course> course = coursesRepository.findById(id);

        if(course.isEmpty()) {
            throw new NotFoundException("Course not found");
        }

        return course.get();
    }
}
