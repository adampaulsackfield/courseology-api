package com.courseology.enrollment;

import com.courseology.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentsRepository extends JpaRepository<Enrollment, Long> {
    @Query(value = "SELECT course.name, course.syllabus, course.author, enrollment.id FROM student JOIN enrollment ON student.id = enrollment.student_id JOIN course ON course.id = enrollment.course_id WHERE student.id = :studentId", nativeQuery = true)
    List<Object[]> getEnrollments(@Param("studentId") Long studentId);
}
