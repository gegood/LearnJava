package com.example.myagent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MyagentApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyagentApplication.class, args);
    }

}
