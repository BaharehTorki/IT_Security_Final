package com.example.demo.data;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Data
@PreAuthorize("hasRole('STUDENT')") //authorization rules at the endpoint level using Spring Security annotations
public class Student {
    @Id
    private int studentId;
    private String name;
    private String gender;
    private int password;
    private String Student(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
