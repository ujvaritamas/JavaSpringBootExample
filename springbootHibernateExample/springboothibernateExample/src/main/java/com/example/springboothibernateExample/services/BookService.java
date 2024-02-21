package com.example.springboothibernateExample.services;

import com.example.springboothibernateExample.model.Author;
import com.example.springboothibernateExample.model.Book;
import com.example.springboothibernateExample.repositories.AuthorRepository;
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

    private AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
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

    @Transactional
    public void addAuthorToBook(Book book, Author author) {
        // Retrieve the author entity from the repository
        Optional<Author> authorOptional = authorRepository.findByName(author.getName());
        Optional<Book> bookOptional = bookRepository.findById(book.getId());

        if (authorOptional.isPresent()) {
            Author persistedAuthor = authorOptional.get();

            // Retrieve the book entity from the repository


            if (bookOptional.isPresent()) {
                Book persistedBook = bookOptional.get();

                // Disassociate the book from its current author, if any
                if (persistedBook.getAuthor() != null) {
                    persistedBook.getAuthor().getBooks().remove(persistedBook);
                }

                // Set the book's author to the new author
                persistedBook.setAuthor(persistedAuthor);
                persistedAuthor.getBooks().add(persistedBook);

                // Save the updated book entity
                bookRepository.save(persistedBook);
            } else {
                // Set the author for the new book
                book.setAuthor(persistedAuthor);
                persistedAuthor.getBooks().add(book);

                // Save the new book entity
                //bookRepository.save(book);
            }
        }
        else {
            //Author not in the database

            if(bookOptional.isPresent()){
                Book persistedBook = bookOptional.get();
                //book is in the database
                if(persistedBook.getAuthor() !=null){
                    //in the end jpa automatically update this author
                    persistedBook.getAuthor().getBooks().remove(persistedBook);
                }
                //in the end new author will be created
                persistedBook.setAuthor(author);
            }
            else{
                //book and author not in the database

                //first save the author
                author.getBooks().add(book);
                authorRepository.save(author);

                book.setAuthor(author);

                bookRepository.save(book);
            }

        }
    }

}
