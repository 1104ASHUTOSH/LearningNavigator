package com.example.lms.controller;

import com.example.lms.entity.Exam;
import com.example.lms.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }

    @GetMapping("/{examId}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long examId) {
        return examService.getExamById(examId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Exam registerExam(@RequestBody Exam exam) {
        return examService.registerExam(exam);
    }

    @PostMapping("/{examId}/register/{studentId}")
    public ResponseEntity<Exam> registerStudentForExam(@PathVariable Long examId, @PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(examService.registerStudentForExam(examId, studentId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{examId}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long examId) {
        examService.deleteExam(examId);
        return ResponseEntity.noContent().build();
    }
}
