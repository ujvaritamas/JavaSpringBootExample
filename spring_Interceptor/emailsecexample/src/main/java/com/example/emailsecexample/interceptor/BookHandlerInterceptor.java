package com.example.emailsecexample.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BookHandlerInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //cals before the handler executed view not generated yet
        //request.getParamter("bookId")...
        //we can perform precheck
        System.out.println("Inside BookHandlerInterceptor.preHandle()");
        return true;
   }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    //execute before veiw generated
    System.out.println("Inside BookHandlerInterceptor.postHandle()");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    //after view rendered
    System.out.println("Inside BookHandlerInterceptor.afterCompletion()");
    }
}
