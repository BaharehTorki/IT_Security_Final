package com.example.demo.data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Data
public class Auth {
    @Id
    private int studentId;
    private String username;
    //-----------------------My change----------------------
    @NotNull
    @Size(min = 8, message = "Be between 8 to 40 characters.\n" +
            "Include upper and lower case letter.\n" +
            "Either number or a Special Character.\n")
    private String password;

    //Constructor with converted password from int to String for facilitate hash password

    public Auth(int studentId, String username, @NotNull String password) {
        this.studentId = studentId;
        this.username = username;
        this.password = hashPassword(password);
    }
    public Auth() {
    }

    //Create method for having hashed password
    private String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
    //-----------------------My change----------------------
}