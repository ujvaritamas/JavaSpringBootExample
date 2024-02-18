package com.example.springboothibernateExample.model;

import jakarta.persistence.*;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="AUTHOR")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy="author", cascade = CascadeType.ALL)
    private Set<Book> books;

    public Author() {
        this.books = new HashSet<>();
    }

    public Author(String name) {
        this.name = name;
        this.books = this.books = new HashSet<>();;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
