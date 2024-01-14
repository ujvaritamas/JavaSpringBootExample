package com.example.PostgresJDBC.dao.integrationtest;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.PostgresJDBC.dao.TestDataUtils;
import com.example.PostgresJDBC.dao.impl.AuthorDaoImpl;
import com.example.PostgresJDBC.mainpkg.Author;


@SpringBootTest
@ExtendWith(SpringExtension.class)
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
}
