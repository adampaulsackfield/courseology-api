package com.courseology.enrollment;

import com.courseology.exception.CustomException;
import com.courseology.exception.NotFoundException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentsController {
    EnrollmentsService enrollmentsService;

    @Autowired
    public EnrollmentsController(EnrollmentsService enrollmentsService) {
        this.enrollmentsService = enrollmentsService;
    }

    @ExceptionHandler
    public ResponseEntity<String> handleExceptions(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleExceptions(CustomException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> enrollCourse(@PathVariable long id, @RequestAttribute("claims") Claims claims) {
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentsService.enrollCourse(id, claims.getSubject()));
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getEnrollments(@RequestAttribute("claims") Claims claims) {
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentsService.getEnrollments(claims.getSubject()));
    }
}
