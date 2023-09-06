package com.example.demo.repository;

import com.example.demo.data.Pii;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Map;

public interface PiiRepository {
    @PreAuthorize("hasRole('ROLE_ADMIN')") //My change----------------------
    List<Map<String, Object>> getPII(String studentId);

}
