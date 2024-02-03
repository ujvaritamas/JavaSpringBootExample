package com.example.demo;


import org.springframework.stereotype.Component;

@Component
public class SimpleAdder {
    public int add(int a, int b) {
        return a + b;
    }
}
