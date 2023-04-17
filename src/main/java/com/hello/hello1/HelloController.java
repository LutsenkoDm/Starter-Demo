package com.hello.hello1;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.test.starter.config.StarterMyConfiguration;
import ru.test.starter.service.MyService;

@RestController
public class HelloController {

    public static final String MY_SERVICE_BEAN_NAME = "myService";
    MyService myService = null;

    @PostConstruct
    public void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(StarterMyConfiguration.class);
        if (context.containsBean(MY_SERVICE_BEAN_NAME)) {
            myService = (MyService) context.getBean("myService");
        }
    }


    @GetMapping("/hello")
    public String hello() {
        if (null != myService) {
            return myService.doSomething();
        }
        return "Hello";
    }
}