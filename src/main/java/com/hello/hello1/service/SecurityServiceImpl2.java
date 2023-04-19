package com.hello.hello1.service;

import com.hello.hello1.annotations.MySuperCoolSecurity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Closeable;
import java.io.IOException;

@Service
@Qualifier("SecurityServiceImpl2")
public class SecurityServiceImpl2 implements SecurityService, Closeable {
    @Override
    @MySuperCoolSecurity
    public boolean auth(String login, String password) {
        System.out.println();
        System.out.println("From service2 " + login + " " + password);
        return true;
    }

    @Override
    public void close()  {
        System.out.println("CLOSE imlp2");
    }
}
