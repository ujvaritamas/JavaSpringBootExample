package com.example.springboothibernateExample;

import com.example.springboothibernateExample.model.Author;
import com.example.springboothibernateExample.model.Book;
import com.example.springboothibernateExample.services.AuthorService;
import com.example.springboothibernateExample.services.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class SpringboothibernateExampleApplicationTests {

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testThatImportSqlPerformedCorrectly(){
		List<Book> books = bookService.list();

		//Assertions.assertEquals(books.size(), 3);
	}

	@Test
	public void testThatsave(){
		List<Book> books = bookService.list();

		Assertions.assertEquals(books.size(), 3);

		Book myBook = new Book(4L, "TTT", null);

		bookService.save(myBook, myBook.getId());

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

	@Test
	@Transactional
	public void testAuthorBook(){
		Author author = new Author("ExampleAuthor0" );
		authorService.saveAuthor(author);

		Book book = new Book(100L, "examplebook", null);

		bookService.save(book, book.getId());

		bookService.addAuthorToBook(book, author);

		Optional<Book> b = bookService.getBook(book.getId());

		Assertions.assertEquals(b.isPresent(), true);

		Assertions.assertEquals(b.get().getAuthor().getName(), author.getName());

		Author a = authorService.getAuthorByName(author.getName());

		Assertions.assertEquals(a.getBooks().size(), 1);
	}

	@Test
	public void testAuthorAndBookNotInDB(){
		Book book = new Book(100L, "examplebook", null);
		Author author = new Author("testAuthorAndBookNotInDB_testauthor");

		Author getAuthor = authorService.getAuthorByName(author.getName());
		Optional<Book> getBook = bookService.getBook(book.getId());

        Assertions.assertFalse(getBook.isPresent());

        Assertions.assertNull(getAuthor);

		bookService.addAuthorToBook(book, author);

		getBook = bookService.getBook(book.getId());
		Assertions.assertTrue(getBook.isPresent());

		getAuthor = authorService.getAuthorByName(author.getName());
		Assertions.assertEquals(author.getName(), getAuthor.getName());

		Assertions.assertEquals(getAuthor.getId(), getBook.get().getAuthor().getId());
	}

	@Test
	public void testAuthorInAndBookNotInDB() {
		Book book = new Book(101L, "examplebook", null);
		Author author = new Author("testAuthorAndBookNotInDB_testauthor1");

		authorService.saveAuthor(author);

		bookService.addAuthorToBook(book, author);

		Author persistedAuthor = authorService.getAuthorByName(author.getName());
		Optional<Book> persistedBook = bookService.getBook(book.getId());

		Assertions.assertEquals(persistedAuthor.getId(), persistedBook.get().getAuthor().getId());
	}
}
