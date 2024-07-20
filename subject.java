package com.example.lms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;

    private String name;

    @ManyToMany(mappedBy = "subjects")
    private List<Student> students;
}
