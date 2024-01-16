package com.example.PostgresJDBC.repositories;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.example.PostgresJDBC.TestDataUtils;
import com.example.PostgresJDBC.mainpkg.Author;
import com.example.PostgresJDBC.mainpkg.Book;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)		//needed for cleanup after testcase finished
public class BookRepositoryIntegrationTests {
	
	private AuthorRepository authorRepo;
	
	private BookRepository underTest;

	@Autowired
	public BookRepositoryIntegrationTests(BookRepository underTest, AuthorRepository authorRepo) {
		super();
		this.underTest = underTest;
		this.authorRepo = authorRepo;
	}
	
	@Test
	public void testThatBookCanBeCreatedAndRecalled() {
		
		//create author if it is not exists (it is required for the book)
		Author author = TestDataUtils.createTestAuthor();
		Book book = TestDataUtils.createTestBook(authorRepo, author);
				
		underTest.save(book);

		Optional<Book> result = underTest.findById(book.getIsbn());
		Assertions.assertThat(result).isPresent();
		Assertions.assertThat(result.get()).isEqualTo(book);
	}
	
	@Test
	public void testThatMultipleBookCanBeCreatedAndRecalled() {
		Author authorA = TestDataUtils.createTestAuthorA();
		Book bookA = TestDataUtils.createTestBookWithAuthor(authorRepo, authorA, "TestA");
		underTest.save(bookA);
		
		Book bookAA = TestDataUtils.createTestBookWithAuthor(authorRepo, authorA, "TestAA");
		underTest.save(bookAA);
		
		Author authorB = TestDataUtils.createTestAuthorB();
		Book bookB = TestDataUtils.createTestBookWithAuthor(authorRepo, authorB, "TestB");
		underTest.save(bookB);
		
		Iterable<Book> result = underTest.findAll();

		Assertions.assertThat(result)
		.hasSize(3)
		.containsExactly(bookA, bookAA, bookB);
	}
	
//	@Test
//	public void testThatUpdateBook() {
//		Author authorA = TestDataUtils.createTestAuthorA();
//		Book bookA = TestDataUtils.createTestBookWithAuthor(authorDao, authorA, "TestA");
//		underTest.create(bookA);
//		
//		Book newBook = new Book("ttt", "AAA title", authorA.getId());
//		
//		bookA.setTitle("AAA");
//		
//		underTest.update(newBook, bookA.getIsbn());
//		
//		Optional<Book> result = underTest.findOne(newBook.getIsbn());
//		Assertions.assertThat(result).isPresent();
//		Assertions.assertThat(result.get()).isEqualTo(newBook);
//		
//		Optional<Book> resultlast = underTest.findOne(bookA.getIsbn());
//		Assertions.assertThat(resultlast).isEmpty();
//	}
//	
//	@Test
//	public void testThatDeleteBook() {
//		Book book = TestDataUtils.createTestBook(authorDao);
//		underTest.create(book);
//		Optional<Book> result = underTest.findOne(book.getIsbn());
//		Assertions.assertThat(result).isPresent();
//		
//		underTest.delete(book.getIsbn());
//		result = underTest.findOne(book.getIsbn());
//		Assertions.assertThat(result).isEmpty();
//	}

}
