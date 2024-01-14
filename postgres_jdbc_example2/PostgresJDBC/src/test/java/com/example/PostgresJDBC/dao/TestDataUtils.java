package com.example.PostgresJDBC.dao;

import com.example.PostgresJDBC.dao.impl.AuthorDaoImpl;
import com.example.PostgresJDBC.mainpkg.Author;
import com.example.PostgresJDBC.mainpkg.Book;

public final class TestDataUtils {

	private TestDataUtils() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static Author createTestAuthor() {
		return new Author(1L, "Test Name1", 32);
	}
	
	public static Book createTestBook(AuthorDaoImpl authorDao) {
		
		if(authorDao.findOne(1L).isPresent()) {
			
		}
		else {
			Author author = TestDataUtils.createTestAuthor();
			authorDao.create(author);
		}
		
		return new Book("myISBN", "Test title1", 1L);
	}
	
}
