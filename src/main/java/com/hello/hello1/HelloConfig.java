package com.hello.hello1;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@ConditionalOnBean(name = "headerInterceptor")
public class HelloConfig implements WebMvcConfigurer {

    @Autowired(required = false)
    @Qualifier("headerInterceptor")
    HandlerInterceptor headerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (headerInterceptor != null) {
            registry.addInterceptor(headerInterceptor);
        }
    }

}
