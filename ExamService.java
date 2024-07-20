package com.example.lms.service;

import com.example.lms.entity.Exam;
import com.example.lms.entity.Student;
import com.example.lms.repository.ExamRepository;
import com.example.lms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Optional<Exam> getExamById(Long examId) {
        return examRepository.findById(examId);
    }

    public Exam registerExam(Exam exam) {
        return examRepository.save(exam);
    }

    public Exam registerStudentForExam(Long examId, Long studentId) throws Exception {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new Exception("Exam not found"));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new Exception("Student not found"));

        if (!student.getSubjects().contains(exam.getSubject())) {
            throw new Exception("Student not enrolled in the subject");
        }

        exam.getStudents().add(student);
        return examRepository.save(exam);
    }

    public void deleteExam(Long examId) {
        examRepository.deleteById(examId);
    }
}
