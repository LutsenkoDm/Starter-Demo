package com.hello.hello1.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MySuperCoolSecurityService  {
    public boolean auth(String login, String password) {
        if (SecurityService.LOGINS.contains(login)) {
            System.out.println("    !!! " + login);
        }
        System.out.println("@@@");
        return false;
    }
}
