package com.example.PostgresJDBC.dao;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.PostgresJDBC.dao.impl.AuthorDaoImpl;
import com.example.PostgresJDBC.mainpkg.Author;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private AuthorDaoImpl underTest;
	
	@Test
	public void testThatCreateAuthorGeneratesCorrectSql() {
		
		Author author = new Author(1L, "Test Name1", 32);
		
		underTest.create(author);
		
		verify(jdbcTemplate).update(
				ArgumentMatchers.eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
				ArgumentMatchers.eq(1L), ArgumentMatchers.eq("Test Name1"),  ArgumentMatchers.eq(32)); 
	}
	
	@Test
	public void testThatFindOneGenerateCorrectSql() {
		underTest.findOne(1L);
		verify(jdbcTemplate).query(ArgumentMatchers.eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"), 
				ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(), ArgumentMatchers.eq(1L));
	}
	
	@Test
	public void testThatFindManyGeneratesCorrectSql() {
		underTest.find();
		
		verify(jdbcTemplate).query(ArgumentMatchers.eq("SELECT id, name, age FROM authors"), 
				ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any());
	}
	
	@Test
	public void testThatUpdateGeneratesCorrectSql() {
		Author author = TestDataUtils.createTestAuthor();
		underTest.update(author, author.getId());
		
		verify(jdbcTemplate).update(ArgumentMatchers.eq("UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?"), 
				ArgumentMatchers.eq(author.getId()),
				ArgumentMatchers.eq(author.getName()),
				ArgumentMatchers.eq(author.getAge()),
				ArgumentMatchers.eq(author.getId()));
	}
	
	@Test
	public void testThatDeleteGeneratesCorrectSql() {
		underTest.delete(1L);
		
		verify(jdbcTemplate).update(ArgumentMatchers.eq("DELETE from authors WHERE id = ?"), 
				ArgumentMatchers.eq(1L));
	}
}
