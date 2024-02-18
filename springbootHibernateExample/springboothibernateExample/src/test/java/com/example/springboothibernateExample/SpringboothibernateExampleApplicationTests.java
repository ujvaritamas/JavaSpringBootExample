package com.example.springboothibernateExample;

import com.example.springboothibernateExample.model.Book;
import com.example.springboothibernateExample.services.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class SpringboothibernateExampleApplicationTests {

	@Autowired
	private BookService bookService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testThatImportSqlPerformedCorrectly(){
		List<Book> books = bookService.list();

		Assertions.assertEquals(books.size(), 3);
	}

	@Test
	public void testThatsave(){
		List<Book> books = bookService.list();

		Assertions.assertEquals(books.size(), 3);

		Book myBook = new Book();
		myBook.setName("TTT");

		bookService.save(myBook, 4L);

		Optional<Book> tBook = bookService.getBook(myBook.getId());
		tBook.ifPresent(value -> Assertions.assertEquals("TTT", value.getName()));
	}

	@Test
	public void testchangeBook(){
		Optional<Book> book = bookService.getBook(1L);
        book.ifPresent(value -> Assertions.assertEquals(value.getName(), "The Tartar Steppe"));

		bookService.changeBookName(1L, "AAA");
		book = bookService.getBook(1L);
		book.ifPresent(value -> Assertions.assertEquals("AAA", value.getName()));

	}

}
