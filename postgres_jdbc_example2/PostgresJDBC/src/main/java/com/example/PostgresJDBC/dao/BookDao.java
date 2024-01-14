package com.example.PostgresJDBC.dao;

import java.util.List;
import java.util.Optional;

import com.example.PostgresJDBC.mainpkg.Book;

public interface BookDao {
	void create(Book book);
	
	Optional<Book> findOne(String isb);
	
	List<Book> find();
	
	void update(Book book, String id);
}
