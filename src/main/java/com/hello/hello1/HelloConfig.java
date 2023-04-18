package com.hello.hello1;

import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HelloConfig implements WebMvcConfigurer {
    public static final String HEADER_INTERCEPTOR_NAME = "headerInterceptor";
    public static final String STARTER_CONFIG_CLASS_NAME = "ru.test.starter.config.StarterMyConfiguration";

    // Кажется это стоило бы писать в стартере, я так делал но тогда его не видно
    // Разве что сделать такой WebMvcConfigurer в интерцепотре и явно добавлять в текущий контекст
    @Override
    @ConditionalOnClass(name = STARTER_CONFIG_CLASS_NAME)
    public void addInterceptors(InterceptorRegistry registry) {
        try {
            Class<?> starterConfigName = Class.forName(STARTER_CONFIG_CLASS_NAME);
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(starterConfigName);
            if (context.containsBean(HEADER_INTERCEPTOR_NAME)) {
                HandlerInterceptor headerInterceptor = (HandlerInterceptor) context.getBean(HEADER_INTERCEPTOR_NAME);
                registry.addInterceptor(headerInterceptor);
            }
        } catch (Exception e) {
        }
    }

/*    @Override
    @SneakyThrows
    @ConditionalOnClass(name = STARTER_CONFIG_CLASS_NAME)
    public void addInterceptors(InterceptorRegistry registry) {

        Class<?> starterConfigName = Class.forName(STARTER_CONFIG_CLASS_NAME);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(starterConfigName);
        if (context.containsBean(HEADER_INTERCEPTOR_NAME)) {
            HandlerInterceptor headerInterceptor = (HandlerInterceptor) context.getBean(HEADER_INTERCEPTOR_NAME);
            registry.addInterceptor(headerInterceptor);
        }
    }*/
}
