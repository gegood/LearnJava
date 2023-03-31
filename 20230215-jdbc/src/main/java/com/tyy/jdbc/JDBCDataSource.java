package com.tyy.jdbc;

import com.tyy.common.Student;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component("jdbccon-datasource")
@PropertySource("classpath:jdbc.properties")
public class JDBCDataSource {

    @Autowired
    private DataSource ds;


    public List<Student> createOneCon(int gender, int grade){

        List<Student> mylist = new ArrayList<Student>();

        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = ds.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT id, name, gender, grade, score FROM students " +
                    "WHERE gender=? AND grade=?")) {
                ps.setObject(1, gender); // 注意：索引从1开始
                ps.setObject(2, grade);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        mylist.add(new Student( // new User object:
                                rs.getLong("id"), // id
                                rs.getString("name"), // name
                                rs.getInt("gender"), // gender
                                rs.getInt("grade"), // grade
                                rs.getInt("score"))
                        ); // score
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mylist;
    }
}
