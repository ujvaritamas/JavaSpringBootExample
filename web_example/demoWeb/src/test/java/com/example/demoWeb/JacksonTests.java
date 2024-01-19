package com.example.demoWeb;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.example.demoWeb.domain.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTests {

	@Test
	public void testThatObjectMapperCanCreateJsonFromObj() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		Book book = new Book("my_isbn", "my_title", "my_author", "my_publishDate");
		String result = objectMapper.writeValueAsString(book);
		assertThat(result).isEqualTo("{\"isbn\":\"my_isbn\",\"title\":\"my_title\",\"author\":\"my_author\",\"yearPublished\":\"my_publishDate\"}");
	}
}
