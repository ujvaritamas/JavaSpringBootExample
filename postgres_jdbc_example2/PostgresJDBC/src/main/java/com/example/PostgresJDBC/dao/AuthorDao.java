package com.example.PostgresJDBC.dao;

import java.util.Optional;

import com.example.PostgresJDBC.mainpkg.Author;

public interface AuthorDao {
	void create(Author author);
	Optional<Author> findOne(Long id);
}
