package com.example.RestAPIProj.controllers;

import com.example.RestAPIProj.TestDataUtils;
import com.example.RestAPIProj.domain.dto.BookDto;
import com.example.RestAPIProj.domain.entities.AuthorEntity;
import com.example.RestAPIProj.domain.entities.BookEntity;
import com.example.RestAPIProj.services.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private BookService bookService;

    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, BookService bookService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.bookService = bookService;
    }

    @Test
    public void testThatCreateBookReturnsHttpStatus201Created() throws Exception {
        BookDto book = TestDataUtils.createTestBookDto(null);

        String bookJson = objectMapper.writeValueAsString(book);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/test_isbn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateBookSuccessfullyReturnsSavedBook() throws Exception {
        BookDto book = TestDataUtils.createTestBookDto(null);

        String bookJson = objectMapper.writeValueAsString(book);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/test_isbn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("isbn").value("test_isbn")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("title").value(book.getTitle())
        );
    }

    @Test
    public void testThatGetBooksReturnsHttp200OK() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetBooksReturnsWithCorrectList() throws Exception {
        BookEntity book = TestDataUtils.createTestBook(null);

        bookService.createBook("ttt", book);

        book.setTitle("ztnhg");
        bookService.createBook("zzz", book);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isbn").value("ttt")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].isbn").value("zzz")
        );
    }

    @Test
    public void testThatGetBookReturnsWithHttp200OK() throws Exception {
        BookEntity book = TestDataUtils.createTestBook(null);
        bookService.createBook("ttt", book);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/"+book.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }

    @Test
    public void testThatGetBookReturnsWithHttpNotFound() throws Exception {
        BookEntity book = TestDataUtils.createTestBook(null);
        bookService.createBook("ttt", book);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/"+book.getIsbn()+"hgfhdgf")
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }
}
