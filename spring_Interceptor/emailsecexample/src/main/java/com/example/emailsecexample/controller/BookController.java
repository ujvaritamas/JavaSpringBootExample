package com.example.emailsecexample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.emailsecexample.model.Book;

@RestController
public class BookController {

    @GetMapping(value = "/")
    public String wellcome(){
        return "<h2>Hello</h2>";
    }

    @RequestMapping(value = "/book")
    public ResponseEntity<Object> getBook(@RequestParam("bookId") Long bookId){
        System.out.println("inside the BookController.getBook()");
        return new ResponseEntity<>(new Book(bookId, "hh", "tt"), HttpStatus.OK);
    }
    
}
