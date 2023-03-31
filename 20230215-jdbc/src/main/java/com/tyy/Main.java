package com.tyy;

import com.tyy.common.Student;
import com.tyy.jdbc.JDBCDataSource;
import com.tyy.jdbctemplate.UserService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;


@Configuration
@ComponentScan
@PropertySource("classpath:jdbc.properties")
public class Main {

    @Value("${jdbc.url}")
    private String JDBC_URL;

    @Value("${jdbc.username}")
    private String JDBC_USER;

    @Value("${jdbc.password}")
    private String JDBC_PASSWORD;

    @Bean
    DataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JDBC_URL);
        config.setUsername(JDBC_USER);
        config.setPassword(JDBC_PASSWORD);
        config.addDataSourceProperty("autoCommit", "true");
        config.addDataSourceProperty("connectionTimeout", "5");
        config.addDataSourceProperty("idleTimeout", "60");
        return new HikariDataSource(config);
    }

    @Bean
    JdbcTemplate createJdbcTemplate(@Autowired DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

//        // 使用原始的jdbc
//        JDBCCon jdbcCon = (JDBCCon) context.getBean("jdbccon");
//        List<Student> mylist01 = jdbcCon.createOneCon();
//
//        for (Student student : mylist01) {
//            System.out.println(student.toString());
//        }
//
        // 使用DataSource连接池
        System.out.println("-------使用 DataSource  ---------");
        JDBCDataSource jdbcCondatasource = (JDBCDataSource) context.getBean("jdbccon-datasource");

        int gender = 0;
        int grade = 3;
        List<Student> mylist02 = jdbcCondatasource.createOneCon(gender, grade);

        for (Student student : mylist02) {
            System.out.println(student.toString());
        }

        // 使用 jdbc tamplate
        System.out.println("-------使用 JDBC Tamplate---------");
        UserService userservice = (UserService) context.getBean("userservice");

//        Student std = userservice.getStudentById(4);
//        System.out.println(std.toString());

        List<Student> mylist03 = userservice.getStudentByGenderGrade(gender, grade);
        for (Student student : mylist03) {
            System.out.println(student.toString());
        }

    }

}
