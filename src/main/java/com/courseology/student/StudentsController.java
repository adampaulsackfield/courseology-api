package com.courseology.student;

import com.courseology.exception.CustomException;
import com.courseology.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.Data;

@RestController
@RequestMapping(value = "/student")
//@CrossOrigin(origins = "http://localhost:3000")
public class StudentsController {
    StudentsService studentsService;

    @Autowired
    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @ExceptionHandler
    public ResponseEntity<String> handleExceptions(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleExceptions(CustomException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
    }

    @Data
    public static class LoginForm {
        String email;
        String password;
    }
    @PostMapping
    // CREATE
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        studentsService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginStudent(@RequestBody LoginForm loginForm) {
        return ResponseEntity.status(HttpStatus.OK).body(studentsService.loginStudent(loginForm.email, loginForm.password));
    }

    @GetMapping("/profile")
    public ResponseEntity<Student> getProfile(@RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.OK).body(studentsService.getProfile(token));
    }
}