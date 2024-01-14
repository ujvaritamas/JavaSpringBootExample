package com.example.PostgresJDBC.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.PostgresJDBC.dao.BookDao;
import com.example.PostgresJDBC.mainpkg.Book;

@Component
public class BookDaoImpl implements BookDao {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public BookDaoImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void create(Book book) {
		jdbcTemplate.update(
				"INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)",
				book.getIsbn(), book.getTitle(), book.getAuthorId()
				);
		
	}

	@Override
	public Optional<Book> findOne(String isbn) {
		List<Book> results = jdbcTemplate.query("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1", new AuthorRowMapper(), isbn);
		
		return results.stream().findFirst();
	}
	
	public static class AuthorRowMapper implements RowMapper<Book>{

		@Override
		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Book(
					rs.getString("isbn"),
					rs.getString("title"),
					rs.getLong("author_id")
					);
		}
		
	}
}
