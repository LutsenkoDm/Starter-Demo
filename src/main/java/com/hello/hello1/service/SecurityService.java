package com.hello.hello1.service;


import java.util.Set;

public interface SecurityService {

    Set<String> LOGINS = Set.of("l1", "l2", "l3");

    boolean auth(String login, String password);
}