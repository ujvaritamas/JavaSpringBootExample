package com.example.RestAPIProj.controllers;

import com.example.RestAPIProj.TestDataUtils;
import com.example.RestAPIProj.domain.entities.AuthorEntity;
import com.example.RestAPIProj.services.AuthorService;
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
public class AuthorControllerIntegrationTests {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private AuthorService authorService;

    @Autowired
    public AuthorControllerIntegrationTests(MockMvc mockMvc, AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.authorService = authorService;
    }


    @Test
    public void testThatCreateAuthorSuccessfullyReturnsHttp201Created() throws Exception {

        AuthorEntity testAuthorA = TestDataUtils.createTestAuthorA();
        testAuthorA.setId(null);

        String authorJson = objectMapper.writeValueAsString(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {

        AuthorEntity testAuthorA = TestDataUtils.createTestAuthorA();
        testAuthorA.setId(null);

        String authorJson = objectMapper.writeValueAsString(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(testAuthorA.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(testAuthorA.getAge())
        );
    }

    @Test
    public void testThatGetAuthorsReturnsHttp200Ok() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAuthorsReturnWithCorrectList() throws Exception {
        AuthorEntity author0 = TestDataUtils.createTestAuthorA();
        AuthorEntity author1 = TestDataUtils.createTestAuthorB();

        authorService.createAuthor(author0);
        authorService.createAuthor(author1);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value(author0.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].name").value(author1.getName())
        );
    }

    @Test
    public void testThatGetAuthorReturnsWithHttp200OK() throws Exception {
        AuthorEntity author0 = TestDataUtils.createTestAuthorA();
        authorService.createAuthor(author0);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/"+author0.getId())
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAuthorReturnsWithHttpNotFound() throws Exception {
        AuthorEntity author0 = TestDataUtils.createTestAuthorA();
        authorService.createAuthor(author0);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/"+author0.getId()+88)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetAuthorReturnsWithCorrectAuthor() throws Exception {
        AuthorEntity author0 = TestDataUtils.createTestAuthorA();
        authorService.createAuthor(author0);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/"+author0.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(author0.getName())
        );
    }
}
