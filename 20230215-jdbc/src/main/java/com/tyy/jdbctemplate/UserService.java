package com.tyy.jdbctemplate;

import com.tyy.common.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Component("userservice")
public class UserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    String mysql_st = "SELECT id, name, gender, grade, score FROM students WHERE gender=? AND grade=?";

    public List<Student> getStudentByGenderGrade(int gender, int grade) {

        return jdbcTemplate.query(mysql_st,
                new BeanPropertyRowMapper<>(Student.class),
                gender, grade);
    }

    // 首先我们看T execute(ConnectionCallback<T> action)方法，它提供了Jdbc的Connection供我们使用
    public Student getStudentById(int id) {
        // 注意传入的是ConnectionCallback:
        return jdbcTemplate.execute((Connection conn) -> {
            // 可以直接使用conn实例，不要释放它，回调结束后JdbcTemplate自动释放:
            // 在内部手动创建的PreparedStatement、ResultSet必须用try(...)释放:
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM students WHERE id = ?")) {
                ps.setObject(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return new Student( // new User object:
                                rs.getLong("id"), // id
                                rs.getString("name"), // name
                                rs.getInt("gender"), // gender
                                rs.getInt("grade"), // grade
                                rs.getInt("score")); // score
                    }
                    throw new RuntimeException("user not found by id.");
                }
            }
        });
    }
}
