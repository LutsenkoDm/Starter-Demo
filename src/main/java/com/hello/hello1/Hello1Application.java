package com.hello.hello1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.test.starter.StarterApplication;

import java.util.Arrays;

@SpringBootApplication
@EnableAutoConfiguration
public class Hello1Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(Hello1Application.class, args);
	}

}
