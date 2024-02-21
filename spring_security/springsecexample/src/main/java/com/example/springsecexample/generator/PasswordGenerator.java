package com.example.springsecexample.generator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private String pwUser = "testpwuser";
    private String pwAdmin = "testpwadmin";

    public String getUserPw(){
        return encoder.encode(pwUser);
    }

    public String getAdminPw(){
        return encoder.encode(pwAdmin);
    }

}
