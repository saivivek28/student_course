package com.example.studentcourse.controller;

import com.example.studentcourse.entity.Course;
import com.example.studentcourse.entity.Student;
import com.example.studentcourse.service.CourseService;
import com.example.studentcourse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentCourseController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    // Test Operation: Check if controller works
    @GetMapping("/ping")
    @ResponseBody
    public String ping() {
        return "Controller is working!";
    }

    // Read Operation: List all students
    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "list";
    }

    // Create Operation: Show Add Form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("courses", courseService.getAllCourses());
        return "add-student";
    }

    // Create Operation: Handle Form Submission
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student, Model model) {
        try {
            studentService.saveStudent(student);
            return "redirect:/students";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Integrity violation: Student with this email already exists.");
            model.addAttribute("courses", courseService.getAllCourses());
            return "add-student";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while saving the student.");
            model.addAttribute("courses", courseService.getAllCourses());
            return "add-student";
        }
    }

    // Update Operation: Show Update Form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            model.addAttribute("student", student);
            model.addAttribute("courses", courseService.getAllCourses());
            return "update-student";
        }
        return "redirect:/students";
    }

    // Update Operation: Handle Update Submission
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("student") Student student, Model model) {
        try {
            studentService.saveStudent(student);
            return "redirect:/students";
        } catch (Exception e) {
            model.addAttribute("error", "Error updating student.");
            model.addAttribute("courses", courseService.getAllCourses());
            return "update-student";
        }
    }
}
