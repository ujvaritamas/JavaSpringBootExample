package com.example.PostgresJDBC.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.PostgresJDBC.dao.AuthorDao;
import com.example.PostgresJDBC.mainpkg.Author;

@Component
public class AuthorDaoImpl implements AuthorDao {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public AuthorDaoImpl(final JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void create(Author author) {
		
		jdbcTemplate.update(
				"INSERT INTO authors (id, name, age) VALUES (?, ?, ?)",
				author.getId(), author.getName(), author.getAge()
				);
		
	}

	@Override
	public Optional<Author> findOne(Long authorId) {
		// TODO Auto-generated method stub
		
		List<Author> results = jdbcTemplate.query("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1",
				new AuthorRowMapper(), authorId);
		
		return results.stream().findFirst();
	}
	
	public static class AuthorRowMapper implements RowMapper<Author> {

		@Override
		public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Author(
					rs.getLong("id"),
					rs.getString("name"),
					rs.getInt("age")
					);
		}
		
	}

	@Override
	public List<Author> find() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT id, name, age FROM authors",
				new AuthorRowMapper());
	}

	@Override
	public void update(Author author, Long id) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(
				"UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",
				author.getId(), author.getName(), author.getAge(), id
				);
	}




	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("DELETE from authors WHERE id = ?", id);
	}
}
