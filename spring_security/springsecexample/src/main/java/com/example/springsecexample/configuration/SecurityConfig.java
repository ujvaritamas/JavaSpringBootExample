package com.example.springsecexample.configuration;

import com.example.springsecexample.generator.PasswordGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends SecurityConfigurerAdapter {

    //basic security configuration SecurityConfigurerAdapter
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        PasswordGenerator pwg = new PasswordGenerator();

        UserDetails user = User
                .withUsername("testuser0")
                .password(pwg.getUserPw())
                .roles("USER")
                .build();

        UserDetails adminUser = User
                .withUsername("adminuser")
                .password(pwg.getAdminPw())
                .roles("ADMIN")
                .build();

        //basic example, store the user in memory (not just in memory accepted, can be DB here).
        return new InMemoryUserDetailsManager(user, adminUser);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //we can add role to the mapping

    }
}
