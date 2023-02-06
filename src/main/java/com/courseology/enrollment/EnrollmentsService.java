package com.courseology.enrollment;

import com.courseology.student.Student;
import com.courseology.student.StudentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentsService {
    private final StudentsRepository studentsRepository;
    private final EnrollmentsRepository enrollmentsRepository;

    public EnrollmentsService(EnrollmentsRepository enrollmentsRepository, StudentsRepository studentsRepository) {
        this.enrollmentsRepository = enrollmentsRepository;
        this.studentsRepository = studentsRepository;
    }

    public String enrollCourse(long id, String email) {
            Student foundStudent = studentsRepository.getStudentByEmail(email);

            Enrollment enrollment = new Enrollment();

            enrollment.setCourse_id(id);
            enrollment.setStudent_id(foundStudent.getId());

            enrollmentsRepository.save(enrollment);

            return "Success";
    }

    public List<Object[]> getEnrollments(String email) {
        Student foundStudent = studentsRepository.getStudentByEmail(email);

        return enrollmentsRepository.getEnrollments(foundStudent.getId());
    }
}

