package usedao.dao;

import com.tyy.common.Student;
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
public class AppConfig {

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

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 使用 DAO 类
        System.out.println("-------使用 DAO 类---------");
        StudentDao studentDao = (StudentDao) context.getBean("studentDao");

        List<Student> studentList = studentDao.getAll(1);

        for(Student std : studentList){
            System.out.println(std.toString());
        }


    }
}
