package com.example.demo.data;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.access.prepost.PreAuthorize;

@Entity
@Data
public class Course {
    @Id
    private int courseId;
    private String courseName;
}
