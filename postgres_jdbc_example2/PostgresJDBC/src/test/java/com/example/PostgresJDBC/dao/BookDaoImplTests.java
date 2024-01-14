package com.example.PostgresJDBC.dao;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.PostgresJDBC.dao.impl.AuthorDaoImpl;
import com.example.PostgresJDBC.dao.impl.BookDaoImpl;
import com.example.PostgresJDBC.mainpkg.Book;



@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private BookDaoImpl underTest;
	
	@Test
	public void testThatCreateBookGeneratesCorrectSql() {
		
		Book author = new Book("myISBN", "Test title1", 1L);
		
		underTest.create(author);
		
		verify(jdbcTemplate).update(
				ArgumentMatchers.eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
				ArgumentMatchers.eq("myISBN"), ArgumentMatchers.eq("Test title1"),  ArgumentMatchers.eq(1L)); 
	}
	
	@Test
	public void testThatFindOneGenerateCorrectSql() {
		underTest.findOne("test-isbn");
		
		verify(jdbcTemplate).query(ArgumentMatchers.eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"), 
				ArgumentMatchers.<BookDaoImpl.AuthorRowMapper>any(), ArgumentMatchers.eq("test-isbn"));
	}
	
	@Test
	public void testThatFindManyGeneratesCorrectSql() {
		underTest.find();
		
		verify(jdbcTemplate).query(ArgumentMatchers.eq("SELECT isbn, title, author_id FROM books"), 
				ArgumentMatchers.<BookDaoImpl.AuthorRowMapper>any());
	}
	
	@Test
	public void testThatUpdateGeneratesCorrectSql() {
		Book book = new Book("myISBN", "Test title1", 1L);
		
		underTest.update(book, book.getIsbn());
		
		verify(jdbcTemplate).update(ArgumentMatchers.eq("UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?"), 
				ArgumentMatchers.eq("myISBN"), ArgumentMatchers.eq("Test title1"), ArgumentMatchers.eq(1L), ArgumentMatchers.eq("myISBN"));
	}
}
