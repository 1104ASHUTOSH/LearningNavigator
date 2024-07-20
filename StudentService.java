package com.example.lms.service;

import com.example.lms.entity.Student;
import com.example.lms.entity.Subject;
import com.example.lms.repository.StudentRepository;
import com.example.lms.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long studentId) {
        return studentRepository.findById(studentId);
    }

    public Student registerStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student enrollSubject(Long studentId, Long subjectId) throws Exception {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new Exception("Student not found"));
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new Exception("Subject not found"));
        student.getSubjects().add(subject);
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}
