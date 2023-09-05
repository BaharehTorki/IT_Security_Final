package com.example.demo.data;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Data
@PreAuthorize("hasRole('ROLE_Student')") //authorization rules at the endpoint level using Spring Security annotations
public class Student {
    @Id
    private int studentId;
    private String name;
    private String gender;
    private int password;

    private String hashPassword(String password) { //Create method for having hashed password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
