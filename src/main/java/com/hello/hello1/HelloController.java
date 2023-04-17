package com.hello.hello1;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.test.starter.config.StarterMyConfiguration;
import ru.test.starter.service.MyService;

@RestController
public class HelloController {

    @Autowired
    MyService myService;


    @GetMapping("/hello")
    public String hello() {
        return myService.doSomething();
    }
}