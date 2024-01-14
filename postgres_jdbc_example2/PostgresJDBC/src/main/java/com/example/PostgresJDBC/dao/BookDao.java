package com.example.PostgresJDBC.dao;

import java.util.Optional;

import com.example.PostgresJDBC.mainpkg.Book;

public interface BookDao {
	void create(Book book);
	
	Optional<Book> findOne(String isb);
}
