package com.example.demo.data;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.access.prepost.PreAuthorize;

@Data
@Entity
@PreAuthorize("hasRole('ADMIN')")
public class Grade {
    @Id
    private int gradeId;
    private int studentId;
    private int courseId;
    private String grade;
}
