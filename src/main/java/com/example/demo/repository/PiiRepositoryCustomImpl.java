package com.example.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.data.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class PiiRepositoryCustomImpl implements PiiRepository {

    @Autowired
    private EntityManager entityManager;
    Student student = new Student();
    int studentId = student.getStudentId();
    // what about 1 or 1 = 1 --
    public List<Map<String, Object>> getPII(String studentId) {
        //String sql = "SELECT * FROM pii WHERE student_id = " + studentId;
        String sql = "SELECT * FROM students WHERE student_id = ?"; //Secure solution to prevent SQL injection attacks

        List<Map<String, Object>> results = new ArrayList<>();

        // Run the query
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, studentId); // Set the studentId parameter using positional parameter binding
        List<Object[]> rows = query.getResultList();

        // Map rows to your objects (if needed)
        for (Object[] row : rows) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("column1", row[0]);
            resultMap.put("column2", row[1]);
            resultMap.put("column3", row[2]);
            resultMap.put("column4", row[3]);
            resultMap.put("column5", row[4]);
            results.add(resultMap);
        }

        return results;
    }
}
