package com.example.demo.data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Data
@PreAuthorize("hasRole('ROLE_VIEWER')") //authorization rules at the endpoint level using Spring Security annotations
public class Student {
    @Id
    private int studentId;
    @NotNull
    @Size(min=3, message = "name must be at least 3 characters long")
    private String name;
    private String gender;
    private int password;
    public Student() {
    }
}
