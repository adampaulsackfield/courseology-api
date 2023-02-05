package com.courseology.student;

import com.courseology.auth.AuthController;
import com.courseology.auth.BcryptController;
import com.courseology.exception.CustomException;
import com.courseology.exception.NotFoundException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class StudentsService {
    StudentsRepository studentsRepository;
    BcryptController bcryptController;
    AuthController authController;

    @Autowired StudentsService(StudentsRepository studentsRepository, BcryptController bcryptController, AuthController authController) {
        this.studentsRepository = studentsRepository;
        this.bcryptController = bcryptController;
        this.authController = authController;
    }

    // CREATE
    public void addStudent(Student student) {
        String hashedPassword = bcryptController.hashPassword(student.getPassword());
        student.setPassword(hashedPassword);

        studentsRepository.save(student);
    }

    // READ
    public String loginStudent(String email, String password) {
        Student foundStudent = studentsRepository.getStudentByEmail(email);

        boolean isVerified = bcryptController.verifyPassword(password, foundStudent.getPassword());

        if(isVerified) {
            return authController.generateToken(email);
        } else {
            return "Failed to create JWT";
        }
    }

    // READ
    public Student getProfile(String token) {
        Claims claims = authController.validateToken(token);

        if(claims == null) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "Authorization Failed");
        } else {
            String email = claims.getSubject();
            Student foundStudent = studentsRepository.getStudentByEmail(email);
            return foundStudent;
        }
    }
}