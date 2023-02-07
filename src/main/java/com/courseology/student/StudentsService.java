package com.courseology.student;

import com.courseology.auth.AuthController;
import com.courseology.auth.BcryptController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentsService {
    private final StudentsRepository studentsRepository;
    private final BcryptController bcryptController;
    private final AuthController authController;

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
    public Student getProfile(String email) {
        return studentsRepository.getStudentByEmail(email);
    }
}