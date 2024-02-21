package com.example.emailsecexample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.emailsecexample.interceptor.BookHandlerInterceptor;

@Configuration
public class WebApplicationConfiguration implements WebMvcConfigurer{

    @Autowired
    BookHandlerInterceptor bookHandlerInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //we register our interceptor
        registry.addInterceptor(bookHandlerInterceptor);
	}
}
