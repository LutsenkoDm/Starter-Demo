package com.hello.hello1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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