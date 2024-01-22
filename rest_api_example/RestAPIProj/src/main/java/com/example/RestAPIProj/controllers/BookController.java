package com.example.RestAPIProj.controllers;

import com.example.RestAPIProj.domain.dto.BookDto;
import com.example.RestAPIProj.domain.entities.BookEntity;
import com.example.RestAPIProj.mappers.Mapper;
import com.example.RestAPIProj.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    private BookService bookService;

    private Mapper<BookEntity, BookDto> bookMapper;

    @Autowired
    public BookController(BookService bookService, Mapper<BookEntity, BookDto> bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable("isbn") String isbn, @RequestBody BookDto book){
        BookEntity bookEntity = bookMapper.mapFrom(book);
        BookEntity savedEntity = bookService.createBook(isbn, bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(savedEntity), HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public List<BookDto> getBooks(){
        List<BookEntity> books = bookService.findAll();

        List<BookDto> result = new ArrayList<>();

        for(BookEntity book:books){
            result.add(bookMapper.mapTo(book));
        }


        return result;
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<BookDto> findById(@PathVariable("isbn") String isbn){
        Optional<BookEntity> book = bookService.findById(isbn);
        if(!book.isEmpty()){
            return new ResponseEntity<>(bookMapper.mapTo(book.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
