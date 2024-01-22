package com.example.RestAPIProj.services.impl;

import com.example.RestAPIProj.domain.entities.BookEntity;
import com.example.RestAPIProj.repositories.BookRepository;
import com.example.RestAPIProj.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {


    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }

    @Override
    public List<BookEntity> findAll() {
        Iterable<BookEntity> bookIterable = bookRepository.findAll();

        List<BookEntity> result = new ArrayList<>();
        for(BookEntity book: bookIterable){
            result.add(book);
        }

        return result;
    }

    @Override
    public Optional<BookEntity> findById(String isbn) {
        return bookRepository.findById(isbn);
    }

}
