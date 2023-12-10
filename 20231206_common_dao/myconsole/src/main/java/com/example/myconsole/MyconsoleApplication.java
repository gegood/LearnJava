package com.example.myconsole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.myconsole", "com.example.common"})
public class MyconsoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyconsoleApplication.class, args);
    }

}
