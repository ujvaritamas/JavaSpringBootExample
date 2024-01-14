package com.example.PostgresJDBC.dao.integrationtest;


import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.example.PostgresJDBC.dao.TestDataUtils;
import com.example.PostgresJDBC.dao.impl.AuthorDaoImpl;
import com.example.PostgresJDBC.dao.impl.BookDaoImpl;
import com.example.PostgresJDBC.mainpkg.Author;
import com.example.PostgresJDBC.mainpkg.Book;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)		//needed for cleanup after testcase finished
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
	
	@Test
	public void testThatMultipleBookCanBeCreatedAndRecalled() {
		Author authorA = TestDataUtils.createTestAuthorA();
		Book bookA = TestDataUtils.createTestBookWithAuthor(authorDao, authorA, "TestA");
		underTest.create(bookA);
		
		Book bookAA = TestDataUtils.createTestBookWithAuthor(authorDao, authorA, "TestAA");
		underTest.create(bookAA);
		
		Author authorB = TestDataUtils.createTestAuthorB();
		Book bookB = TestDataUtils.createTestBookWithAuthor(authorDao, authorB, "TestB");
		underTest.create(bookB);
		
		List<Book> result= underTest.find();
		Assertions.assertThat(result)
		.hasSize(3)
		.containsExactly(bookA, bookAA, bookB);
	}
	
	@Test
	public void testThatUpdateBook() {
		Author authorA = TestDataUtils.createTestAuthorA();
		Book bookA = TestDataUtils.createTestBookWithAuthor(authorDao, authorA, "TestA");
		underTest.create(bookA);
		
		Book newBook = new Book("ttt", "AAA title", authorA.getId());
		
		bookA.setTitle("AAA");
		
		underTest.update(newBook, bookA.getIsbn());
		
		Optional<Book> result = underTest.findOne(newBook.getIsbn());
		Assertions.assertThat(result).isPresent();
		Assertions.assertThat(result.get()).isEqualTo(newBook);
		
		Optional<Book> resultlast = underTest.findOne(bookA.getIsbn());
		Assertions.assertThat(resultlast).isEmpty();
		
	}

}
