package com.hello.hello1.service;

import com.hello.hello1.annotations.MySuperCoolSecurity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("SecurityServiceImpl2")
public class SecurityServiceImpl2 implements SecurityService, AutoCloseable {
    @Override
    @MySuperCoolSecurity
    public boolean auth(String login, String password) {
        System.out.println();
        System.out.println("From service2 " + login + " " + password);
        return true;
    }

    @Override
    public void close() throws Exception {
        System.out.println("CLOSE 2");
    }
}
