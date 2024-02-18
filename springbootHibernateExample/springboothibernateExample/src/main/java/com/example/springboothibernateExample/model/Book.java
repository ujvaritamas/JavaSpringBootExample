package com.example.springboothibernateExample.model;

import jakarta.persistence.*;

@Entity
@Table(name="BOOK")
public class Book {
    @jakarta.persistence.Id
    private Long Id;
    private String name;

    public Book() {
        Id = null;
    }

    public Book(Long id, String name, Author author) {
        Id = id;
        this.name = name;
        this.author = author;
    }

    @ManyToOne
    @JoinColumn(name="author_id")//, nullable=false)
    private Author author;

    public void setId(Long id) {
        Id = id;
    }

    public Long getId() {
        return Id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
