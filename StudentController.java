package com.example.lms.controller;

import com.example.lms.entity.Student;
import com.example.lms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Student registerStudent(@RequestBody Student student) {
        return studentService.registerStudent(student);
    }

    @PutMapping("/{studentId}/enroll/{subjectId}")
    public ResponseEntity<Student> enrollSubject(@PathVariable Long studentId, @PathVariable Long subjectId) {
        try {
            return ResponseEntity.ok(studentService.enrollSubject(studentId, subjectId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}
