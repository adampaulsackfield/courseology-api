package com.courseology.course;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseDataLoader implements CommandLineRunner {
    JSONObject course_1_plan;

    @Autowired
    CoursesRepository coursesRepository;
    @Override
    public void run(String... args) throws Exception {
        loadCourseData();
    }

    private void loadCourseData() {
        if(coursesRepository.count() == 0) {
            course_1_plan.put("I.", "Introduction to AI");

            Course course_1 = new Course(1L, "Introduction to Artificial Intelligence", "Computer Science", "Introduction to AI concepts and techniques, machine learning, deep learning, computer vision, natural language processing, and robotics.", "Dr. John Doe", 4.9, 8, "Beginner");
//            Course course_2 = new Course(2L, "Data Science Fundamentals", "Data Science", "Data preparation, exploratory data analysis, statistical analysis, machine learning, data visualization, and big data.", "Dr. Jane Doe", 4.8, 12, "Intermediate");
//            Course course_3 = new Course(3L, "Web Development with HTML, CSS, and JavaScript", "Web Development", "Introduction to HTML, CSS, and JavaScript, web design, responsive design, front-end frameworks, and web development tools.", "Mr Steve Smith", 4.7, 10, "Beginner");
//            Course course_4 = new Course(4L, "Object-Oriented Programming with Java", "Computer Science", "Introduction to Java, object-oriented programming concepts, data structures, algorithms, and programming best practices.", "Dr. Sarah Johnson", 4.6, 9, "Intermediate");
//            Course course_5 = new Course(5L, "Cloud Computing Fundamentals", "Computer Science", "Introduction to cloud computing, virtualization, cloud architecture, cloud platforms and services, and cloud security.", "Mr Tom Wilson", 4.5, 7, "Beginner");

            coursesRepository.save(course_1);
//            coursesRepository.save(course_2);
//            coursesRepository.save(course_3);
//            coursesRepository.save(course_4);
//            coursesRepository.save(course_5);
        }
    }
}
