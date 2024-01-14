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
}
