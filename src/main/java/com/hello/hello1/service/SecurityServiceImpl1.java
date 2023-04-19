package com.hello.hello1.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("SecurityServiceImpl1")
public class SecurityServiceImpl1 implements SecurityService {
    @Override
    public boolean auth(String login, String password) {
        System.out.println();
        System.out.println("From service1 " + login + " " + password);
        return true;
    }
}
