package com.example.PostgresJDBC.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.PostgresJDBC.TestDataUtils;
import com.example.PostgresJDBC.mainpkg.Author;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)		//needed for cleanup after testcase finished
public class AuthorRepositoryIntegrationTests {
	
	private AuthorRepository underTest;

	//this is needed because it is SpringBootTest and imp is injected
	@Autowired
	public AuthorRepositoryIntegrationTests(AuthorRepository underTest) {
		super();
		this.underTest = underTest;
	}

	@Test
	public void testThatAuthorCanBeCreatedAndRecalled() {
		Author author = TestDataUtils.createTestAuthor();
		underTest.save(author);
		Optional<Author> result = underTest.findById(author.getId());
		
		Assertions.assertThat(result).isPresent();
		Assertions.assertThat(result.get()).isEqualTo(author);
	}
	
	@Test
	public void testThatMultipleAuthorCanBeCreatedAndRecalled() {
		Author authorA = TestDataUtils.createTestAuthorA();
		underTest.save(authorA);
		Author authorB = TestDataUtils.createTestAuthorB();
		underTest.save(authorB);
		
		Iterable<Author> result = underTest.findAll();
		Assertions.assertThat(result)
			.hasSize(2)
			.containsExactly(authorA, authorB);
	}
	
	@Test
	public void testThatMultipleAuthorCanBeCreatedAndRecalled1() {
		Author authorA = TestDataUtils.createTestAuthorA();
		underTest.save(authorA);
		Author authorB = TestDataUtils.createTestAuthorB();
		underTest.save(authorB);
		
		
		Iterable<Author> result = underTest.findAll();
		Assertions.assertThat(result)
			.hasSize(2)
			.containsExactly(authorA, authorB);
	}
	
	@Test
	public void testThatUpdateAuthor() {
		Author author = TestDataUtils.createTestAuthor();
		underTest.save(author);
		
		author.setAge(10);
		
		underTest.save(author);
		
		Optional<Author> result = underTest.findById(author.getId());
		Assertions.assertThat(result).isPresent();
		Assertions.assertThat(result.get()).isEqualTo(author);
		
	}
	
	@Test
	public void testThatDeleteAuthor() {
		Author author = TestDataUtils.createTestAuthor();
		
		underTest.save(author);
		
		Optional<Author> result = underTest.findById(author.getId());
		Assertions.assertThat(result).isPresent();

		underTest.delete(author);
		result = underTest.findById(author.getId());
		Assertions.assertThat(result).isEmpty();
	}
	
	@Test
	public void testThatGetAuthorsWithAgeLessThan() {
		Author author0 = new Author(1L, "Arhur0", 32);
//		underTest.save(author0);
		Author author1 = new Author(2L, "Arhur1", 88);
//		underTest.save(author1);
		Author author2 = new Author(3L, "Arhur2", 35);
//		underTest.save(author2);
		
		
		Author[] authors = new Author[]{
				author0,
				author1,
				author2
		};
		
		underTest.saveAll(Arrays.asList(authors));
		
		Iterable<Author> result = underTest.ageLessThan(50);
		assertThat(result)
			.hasSize(2)
			.contains(author0)
			.contains(author2);
	}
	
	@Test
	public void testThatGetAuthorsWithAgeGreatherThan() {
		Author author0 = new Author(1L, "Arhur0", 32);
		Author author1 = new Author(2L, "Arhur1", 88);
		Author author2 = new Author(3L, "Arhur2", 35);
		
		
		Author[] authors = new Author[]{
				author0,
				author1,
				author2
		};
		
		underTest.saveAll(Arrays.asList(authors));
		
		Iterable<Author> result = underTest.findAuthorsWithAgeGreatherThan(50);
		
		assertThat(result)
		.hasSize(1)
		.contains(author1);
	}
}


