package com.example.springsecexample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String getHomePage(){
        return "home";
    }

    @GetMapping("/test0")
    public String getTest0(){
        return "test0";
    }

    @GetMapping("/admin")
    public String getAdminPage(){
        return "admin";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }
}
