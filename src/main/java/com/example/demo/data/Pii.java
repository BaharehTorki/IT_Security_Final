package com.example.demo.data;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.access.prepost.PreAuthorize;

@Entity
@Data
public class Pii {
    @Id
    private int piiId;
    private int studentId;
    private String personnummer;
    private String phoneNumber;
    private byte[] biometricData;
}
