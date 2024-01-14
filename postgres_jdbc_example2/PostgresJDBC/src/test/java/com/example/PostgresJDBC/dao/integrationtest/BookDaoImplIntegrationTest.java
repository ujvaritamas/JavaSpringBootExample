package com.example.PostgresJDBC.dao.integrationtest;


import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.PostgresJDBC.dao.TestDataUtils;
import com.example.PostgresJDBC.dao.impl.AuthorDaoImpl;
import com.example.PostgresJDBC.dao.impl.BookDaoImpl;
import com.example.PostgresJDBC.mainpkg.Book;

@SpringBootTest
public class BookDaoImplIntegrationTest {
	
	private AuthorDaoImpl authorDao;
	
	private BookDaoImpl underTest;

	@Autowired
	public BookDaoImplIntegrationTest(BookDaoImpl underTest, AuthorDaoImpl authorDao) {
		super();
		this.underTest = underTest;
		this.authorDao = authorDao;
	}
	
	@Test
	public void testThatBookCanBeCreatedAndRecalled() {
		
		//create author if it is not exists (it is required for the book)
		Book book = TestDataUtils.createTestBook(authorDao);
		
		underTest.create(book);
		Optional<Book> result = underTest.findOne(book.getIsbn());
		Assertions.assertThat(result).isPresent();
		Assertions.assertThat(result.get()).isEqualTo(book);
	}
}
