package com.example.springboothibernateExample.controllers;

import com.example.springboothibernateExample.services.AuthorService;
import com.example.springboothibernateExample.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SimpleController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;



}
