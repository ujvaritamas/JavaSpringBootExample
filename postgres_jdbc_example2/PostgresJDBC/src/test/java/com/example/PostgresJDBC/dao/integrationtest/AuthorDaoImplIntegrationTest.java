package com.example.PostgresJDBC.dao.integrationtest;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.PostgresJDBC.dao.TestDataUtils;
import com.example.PostgresJDBC.dao.impl.AuthorDaoImpl;
import com.example.PostgresJDBC.mainpkg.Author;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)		//needed for cleanup after testcase finished
public class AuthorDaoImplIntegrationTest {
	
	private AuthorDaoImpl underTest;

	//this is needed because it is SpringBootTest and imp is injected
	@Autowired
	public AuthorDaoImplIntegrationTest(AuthorDaoImpl underTest) {
		super();
		this.underTest = underTest;
	}

	@Test
	public void testThatAuthorCanBeCreatedAndRecalled() {
		Author author = TestDataUtils.createTestAuthor();
		underTest.create(author);
		Optional<Author> result = underTest.findOne(author.getId());
		
		Assertions.assertThat(result).isPresent();
		Assertions.assertThat(result.get()).isEqualTo(author);
	}
	
	@Test
	public void testThatMultipleAuthorCanBeCreatedAndRecalled() {
		Author authorA = TestDataUtils.createTestAuthorA();
		underTest.create(authorA);
		Author authorB = TestDataUtils.createTestAuthorB();
		underTest.create(authorB);
		
		List<Author> result = underTest.find();
		Assertions.assertThat(result)
			.hasSize(2)
			.containsExactly(authorA, authorB);
	}
	
	@Test
	public void testThatMultipleAuthorCanBeCreatedAndRecalled1() {
		Author authorA = TestDataUtils.createTestAuthorA();
		underTest.create(authorA);
		Author authorB = TestDataUtils.createTestAuthorB();
		underTest.create(authorB);
		
		List<Author> result = underTest.find();
		Assertions.assertThat(result)
			.hasSize(2)
			.containsExactly(authorA, authorB);
	}
	
	@Test
	public void testThatUpdateAuthor() {
		Author author = TestDataUtils.createTestAuthor();
		Author authorA = TestDataUtils.createTestAuthorA();
		underTest.create(author);
		
		author.setAge(10);
		
		underTest.update(authorA, author.getId());
		
		Optional<Author> result = underTest.findOne(authorA.getId());
		Assertions.assertThat(result).isPresent();
		Assertions.assertThat(result.get()).isEqualTo(authorA);
		
		Optional<Author> resultlast = underTest.findOne(author.getId());
		Assertions.assertThat(resultlast).isEmpty();
	}
	
	@Test
	public void testThatDeleteAuthor() {
		Author author = TestDataUtils.createTestAuthor();
		underTest.create(author);
		
		Optional<Author> result = underTest.findOne(author.getId());
		Assertions.assertThat(result).isPresent();

		underTest.delete(author.getId());
		result = underTest.findOne(author.getId());
		Assertions.assertThat(result).isEmpty();
	}
}
