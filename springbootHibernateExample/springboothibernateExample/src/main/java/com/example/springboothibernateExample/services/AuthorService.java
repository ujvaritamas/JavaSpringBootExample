package com.example.springboothibernateExample.services;

import com.example.springboothibernateExample.model.Author;
import com.example.springboothibernateExample.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void saveAuthor(Author author){
        authorRepository.save(author);
    }

    public Author getAuthorByName(String name){
        Optional<Author> author = authorRepository.findByName(name);
        return author.orElse(null);
    }
}
