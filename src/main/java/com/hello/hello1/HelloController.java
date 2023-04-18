package com.hello.hello1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok()
                .body("Hello");
    }
}