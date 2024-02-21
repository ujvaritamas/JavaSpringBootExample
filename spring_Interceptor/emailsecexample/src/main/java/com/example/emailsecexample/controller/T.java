package com.example.emailsecexample.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class T implements CommandLineRunner{

    @Override
    public void run(String... args) {
        System.out.println("hello");

    }
    
}
