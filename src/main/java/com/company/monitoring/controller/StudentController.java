package com.company.monitoring.controller;

import com.company.monitoring.exception.StudentNotFoundException;
import com.company.monitoring.model.Student;
import com.company.monitoring.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    private List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    private Student getStudent(@PathVariable Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " was not found"));
    }

    @PostMapping
    private Student addStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return student;
    }

    @DeleteMapping("/{id}")
    private void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }
}
