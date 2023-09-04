package com.example.demo.repository;

import com.example.demo.data.Pii;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface PiiRepository {

    List<Map<String, Object>> getPII(String studentId);

}
