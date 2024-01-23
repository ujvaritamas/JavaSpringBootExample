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

    @Override
    public boolean isExists(String isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public BookEntity saveBook(BookEntity book) {
        return bookRepository.save(book);
    }

    @Override
    public BookEntity partialUpdate(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return bookRepository.findById(isbn).map(existingBook -> {
            Optional.ofNullable(book.getTitle()).ifPresent(existingBook::setTitle);
            Optional.ofNullable(book.getAuthorEntity()).ifPresent(existingBook::setAuthorEntity);
            return bookRepository.save(existingBook);
        }).orElseThrow(() -> new RuntimeException("Book not found."));
    }

    @Override
    public BookEntity deleteBook(String isbn) {
        Optional<BookEntity> foundBook = bookRepository.findById(isbn);

        if(foundBook.isPresent()){
            bookRepository.delete(foundBook.get());
            return foundBook.get();
        }
        else{
            throw new RuntimeException("Book does not found");
        }
    }

}
