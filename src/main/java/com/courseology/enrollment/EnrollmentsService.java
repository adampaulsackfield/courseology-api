package com.courseology.enrollment;

import com.courseology.auth.AuthController;
import com.courseology.exception.CustomException;
import com.courseology.student.Student;
import com.courseology.student.StudentsRepository;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentsService {
    private AuthController authController;
    private StudentsRepository studentsRepository;
    private final EnrollmentsRepository enrollmentsRepository;

    public EnrollmentsService(EnrollmentsRepository enrollmentsRepository, AuthController authController, StudentsRepository studentsRepository) {
        this.enrollmentsRepository = enrollmentsRepository;
        this.authController = authController;
        this.studentsRepository = studentsRepository;
    }

    public String enrollCourse(long id, String token) {
        Claims claims = authController.validateToken(token);

        if(claims == null) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "Authorization Failed");
        } else {
            String email = claims.getSubject();
            Student foundStudent = studentsRepository.getStudentByEmail(email);
            Enrollment enrollment = new Enrollment();

            enrollment.setCourse_id(id);
            enrollment.setStudent_id(foundStudent.getId());

            enrollmentsRepository.save(enrollment);

            return "Success";
        }
    }

    public List<Object[]> getEnrollments(String token) {
            Claims claims = authController.validateToken(token);

            if(claims == null) {
                throw new CustomException(HttpStatus.UNAUTHORIZED, "Authorization Failed");
            } else {
                String email = claims.getSubject();

                Student foundStudent = studentsRepository.getStudentByEmail(email);

                List<Object[]> enrollments = enrollmentsRepository.getEnrollments(foundStudent.getId());

                return enrollments;
            }
    }
}

