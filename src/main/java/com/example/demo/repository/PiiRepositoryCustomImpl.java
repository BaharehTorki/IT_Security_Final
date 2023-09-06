package com.example.demo.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.data.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class PiiRepositoryCustomImpl implements PiiRepository {

    @Autowired
    private EntityManager entityManager;

    @Value("${spring.datasource.url}")
    private String SPRING_DATASOURCE_URL_JDBC;

    // what about 1 or 1 = 1 --
    public List<Map<String, Object>> getPII(String studentId) {

        //String sql = "SELECT * FROM pii WHERE student_id = " + studentId;

        //---------------------------My code--------------------------------------
        String sql = "SELECT * FROM students WHERE student_id = ?"; //Secure solution to prevent SQL injection attacks

        try {
            Class.forName(SPRING_DATASOURCE_URL_JDBC);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Create PreparedStatement for secure connection
        try (Connection connection = DriverManager.getConnection(SPRING_DATASOURCE_URL_JDBC)) {
            if (connection != null) {
                System.out.println("\t\t\t" + sql + ", ? is prepared with " + studentId);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, studentId);
                ResultSet resultSet = preparedStatement.executeQuery();
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(studentId)); // Bind the parameter securely
            ResultSet resultSet = preparedStatement.executeQuery();

            //---------------------------Robert code--------------------------------------
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
            //---------------------------Robert code--------------------------------------
            resultSet.close();
            preparedStatement.close();
            return results;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}