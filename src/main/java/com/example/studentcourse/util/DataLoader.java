package com.example.studentcourse.util;

import com.example.studentcourse.entity.Course;
import com.example.studentcourse.entity.Student;
import com.example.studentcourse.repository.CourseRepository;
import com.example.studentcourse.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        // Populate 10 Courses
        Course c1 = new Course("Java Programming", "CS101");
        Course c2 = new Course("Database Systems", "CS102");
        Course c3 = new Course("Web Development", "CS103");
        Course c4 = new Course("Data Structures", "CS104");
        Course c5 = new Course("Algorithms", "CS105");
        Course c6 = new Course("Operating Systems", "CS106");
        Course c7 = new Course("Computer Networks", "CS107");
        Course c8 = new Course("Artificial Intelligence", "CS108");
        Course c9 = new Course("Machine Learning", "CS109");
        Course c10 = new Course("Cloud Computing", "CS110");

        courseRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10));

        // Populate 10 Students
        studentRepository.save(new Student("Alice Johnson", "alice@example.com", c1));
        studentRepository.save(new Student("Bob Smith", "bob@example.com", c2));
        studentRepository.save(new Student("Charlie Brown", "charlie@example.com", c3));
        studentRepository.save(new Student("David Wilson", "david@example.com", c4));
        studentRepository.save(new Student("Eve Davis", "eve@example.com", c5));
        studentRepository.save(new Student("Frank Miller", "frank@example.com", c6));
        studentRepository.save(new Student("Grace Lee", "grace@example.com", c7));
        studentRepository.save(new Student("Heidi York", "heidi@example.com", c8));
        studentRepository.save(new Student("Ivan Drago", "ivan@example.com", c9));
        studentRepository.save(new Student("Judy Hopps", "judy@example.com", c10));
    }
}
