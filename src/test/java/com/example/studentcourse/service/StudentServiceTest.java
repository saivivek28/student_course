package com.example.studentcourse.service;

import com.example.studentcourse.entity.Course;
import com.example.studentcourse.entity.Student;
import com.example.studentcourse.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void testGetAllStudents() {
        Course course = new Course("Java", "CS101");
        Student s1 = new Student("Alice", "alice@example.com", course);
        Student s2 = new Student("Bob", "bob@example.com", course);
        
        when(studentRepository.findAllStudentsWithCourses()).thenReturn(Arrays.asList(s1, s2));
        
        List<Student> result = studentService.getAllStudents();
        
        assertEquals(2, result.size());
        verify(studentRepository, times(1)).findAllStudentsWithCourses();
    }

    @Test
    public void testSaveStudent() {
        Course course = new Course("Java", "CS101");
        Student student = new Student("Alice", "alice@example.com", course);
        
        when(studentRepository.save(student)).thenReturn(student);
        
        Student savedStudent = studentService.saveStudent(student);
        
        assertNotNull(savedStudent);
        assertEquals("Alice", savedStudent.getName());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    public void testGetStudentById() {
        Course course = new Course("Java", "CS101");
        Student student = new Student("Alice", "alice@example.com", course);
        student.setId(1L);
        
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        
        Student result = studentService.getStudentById(1L);
        
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }
}
