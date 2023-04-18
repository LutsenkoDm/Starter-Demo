package com.hello.hello1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.hello", "ru.test.starter"})
public class Hello1Application {

    public static void main(String[] args) {
        SpringApplication.run(Hello1Application.class, args);
    }

}
