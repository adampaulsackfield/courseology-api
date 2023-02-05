package com.courseology.enrollment;

import com.courseology.course.CoursesRepository;
import com.courseology.course.CoursesService;
import com.courseology.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentsController {
    EnrollmentsService enrollmentsService;

    @Autowired
    public EnrollmentsController(EnrollmentsService enrollmentsService) {
        this.enrollmentsService = enrollmentsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> enrollCourse(@PathVariable long id, @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentsService.enrollCourse(id, token));
    }

    @GetMapping
    public ResponseEntity<List<Object[]>> getEnrollments(@RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentsService.getEnrollments(token));
    }
}
