package com.example.lms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    
    private String name;

    @ManyToMany
    @JoinTable(
      name = "student_subject", 
      joinColumns = @JoinColumn(name = "student_id"), 
      inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> subjects;

    @ManyToMany
    @JoinTable(
      name = "student_exam", 
      joinColumns = @JoinColumn(name = "student_id"), 
      inverseJoinColumns = @JoinColumn(name = "exam_id"))
    private List<Exam> exams;
}
