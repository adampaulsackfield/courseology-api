package com.courseology.course;

import com.courseology.exception.CustomException;
import com.courseology.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/course")
@CrossOrigin(origins = "http://localhost:3000")
public class CoursesController {
    private final CoursesService coursesService;

    @Autowired
    public CoursesController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @ExceptionHandler
    public ResponseEntity<String> handleExceptions(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleExceptions(CustomException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
    }

    // POST - Add Course - /course
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        coursesService.addCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    // GET - All Courses - /course
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(@RequestParam(defaultValue = "5") int limit, @RequestParam(required = false) String category) {
        if(category != null) {
            return ResponseEntity.status(HttpStatus.OK).body(coursesService.getCoursesByCategory(limit, category));
        }
        return ResponseEntity.status(HttpStatus.OK).body(coursesService.getAllCourses(limit));
    }

    // GET - Course By ID - /course/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(coursesService.getCourseById(id));
    }
}
