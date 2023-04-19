package com.hello.hello1;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class Hello1Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Hello1Application.class, args);
        BeanDefinition beanDefinition = context.getBeanFactory().getBeanDefinition("securityServiceImpl2");
        System.out.println();
    }

}
