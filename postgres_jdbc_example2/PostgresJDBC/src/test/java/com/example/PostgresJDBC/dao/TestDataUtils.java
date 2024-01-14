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
	
	public static Author createTestAuthorA() {
		return new Author(3L, "Test NameA", 66);
	}
	
	public static Author createTestAuthorB() {
		return new Author(10L, "Test NameB", 55);
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
	
public static Book createTestBookWithAuthor(AuthorDaoImpl authorDao, Author author, String testIsbn) {
		
		if(authorDao.findOne(author.getId()).isPresent()) {
			
		}
		else {
			authorDao.create(author);
		}
		
		return new Book(testIsbn, "Test title1", author.getId());
	}
	
	
}
