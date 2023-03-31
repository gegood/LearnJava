
/***
 * 使用原始的 JDBC 接口去连接数据库
 * 将数据库得到的数据存储到 User 类中
 */

package com.tyy.jdbc;

import com.tyy.common.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component("jdbccon")
@PropertySource("classpath:jdbc.properties")
public class JDBCCon {

    // JDBC连接的URL, 不同数据库有不同的格式:
    @Value("${jdbc.url}")
    private String JDBC_URL;

    @Value("${jdbc.username}")
    private String JDBC_USER;

    @Value("${jdbc.password}")
    private String JDBC_PASSWORD;

    public List<Student>  createOneCon(){

        List<Student> mylist = new ArrayList<Student>();

        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT id, grade, name, gender, score FROM students WHERE gender=? AND grade=?")) {
                ps.setObject(1, 1); // 注意：索引从1开始
                ps.setObject(2, 3);
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

    public void printAll(){

        System.out.println(JDBC_URL);
        System.out.println(JDBC_USER);
        System.out.println(JDBC_PASSWORD);

    }

}
