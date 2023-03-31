package com.tyy;

import com.tyy.beaop.SayHello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan
@Configuration
@EnableAspectJAutoProxy
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        SayHello sayHello = context.getBean(SayHello.class);
        sayHello.sayhello();
    }
}

