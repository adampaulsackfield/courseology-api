package com.courseology.enrollment;

import com.courseology.student.Student;
import com.courseology.student.StudentsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Map<String, Object>> getEnrollments(String email) {
        Student foundStudent = studentsRepository.getStudentByEmail(email);

        List<Map<String, Object>> result = new ArrayList<>();
        List<Object[]> queryResult = enrollmentsRepository.getEnrollments(foundStudent.getId());
        for (Object[] objects : queryResult) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", objects[0]);
            map.put("syllabus", objects[1]);
            map.put("author", objects[2]);
            map.put("id", objects[3]);
            result.add(map);
        }

        return result;
    }
}

