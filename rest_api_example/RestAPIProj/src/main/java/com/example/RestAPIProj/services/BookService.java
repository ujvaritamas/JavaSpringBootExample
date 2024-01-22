package com.example.RestAPIProj.services;

import com.example.RestAPIProj.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity book);
    List<BookEntity> findAll();

    Optional<BookEntity> findById(String isbn);
}
