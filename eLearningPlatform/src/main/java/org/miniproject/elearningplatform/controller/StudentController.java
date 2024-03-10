package org.miniproject.elearningplatform.controller;

import org.miniproject.elearningplatform.model.Student;
import org.miniproject.elearningplatform.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    protected StudentService studentService;

    // Create a new student
    @PostMapping("/add")
    @ResponseBody // Use this annotation to send a JSON response
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    // Retrieve a student by ID
    @GetMapping("/{id}")
    @ResponseBody
    public Student getStudent(@PathVariable String id) {
        return studentService.getStudentById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    // Optional: List all students
    @GetMapping("/all")
    @ResponseBody
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}
