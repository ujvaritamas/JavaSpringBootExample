package com.example.springboothibernateExample.services;

import com.example.springboothibernateExample.model.Book;
import com.example.springboothibernateExample.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> list() {
        return bookRepository.findAll();
    }

    @Transactional
    public void changeBookName(Long bookId, String data){
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        book.setName(data);

        bookRepository.save(book);
    }

    @Transactional
    public Optional<Book> getBook(Long id) {
        // Retrieve the book by its ID
        return bookRepository.findById(id);
    }

    @Transactional
    public void save(Book book, Long id){

        if(bookRepository.findById(id).isEmpty()){
            book.setId(id);
            bookRepository.save(book);
        }
        else{
            System.out.println("ID already in the db");
        }

    }
}
