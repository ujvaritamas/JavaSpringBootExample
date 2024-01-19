package com.example.demoWeb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoWeb.domain.Book;

@RestController
public class BookController {
	
	private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @GetMapping(path = "/books")
    public Book retrieveBook(){
    	//jackson will convert the obj to json
    	return new Book("my_isbn", "my_title", "my_author", "my_publishDate");
    }
    
    @PostMapping(path = "/books")
    public Book createBook(@RequestBody final Book book) {
    	log.info("Got book: "+ book.toString());
    	return book;
    }
}
