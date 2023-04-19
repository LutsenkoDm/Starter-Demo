package com.hello.hello1;

import com.hello.hello1.service.SecurityService;
import com.hello.hello1.service.SecurityServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @Autowired
    @Qualifier("SecurityServiceImpl1")
    SecurityService securityService1;
    @Autowired
    @Qualifier("SecurityServiceImpl2")
    SecurityServiceImpl2 securityService2;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        System.out.println("In controller, service1 return: " + securityService1.auth("l1", "p1"));
        System.out.println("------------------");
        System.out.println("In controller, service2 return: " + securityService2.auth("l2", "p2"));
        System.out.println("------------------");
        System.out.println("In controller, service2 return: " + securityService2.auth("abracadabra", "p3"));
        System.out.println("------------------");
        try {
            securityService2.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok()
                .body("Hello");
    }
}