package com.example.community.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class TestRepository {
    private final JdbcTemplate jdbcTemplate;

    public TestRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> findAllUsers() {
        String sql = "SELECT * FROM test";  // 모든 행 가져오기
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);

        return results;
    }
}
