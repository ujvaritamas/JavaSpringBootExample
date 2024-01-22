package com.example.RestAPIProj.repositories;

import com.example.RestAPIProj.TestDataUtils;
import com.example.RestAPIProj.domain.entities.AuthorEntity;
import com.example.RestAPIProj.domain.entities.BookEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)		//needed for cleanup after testcase finished
public class BookRepositoryIntegrationTests {

    private AuthorRepository authorRepo;

    private BookRepository underTest;

    @Autowired
    public BookRepositoryIntegrationTests(BookRepository underTest, AuthorRepository authorRepo) {
        super();
        this.underTest = underTest;
        this.authorRepo = authorRepo;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {

        //create author if it is not exists (it is required for the book)
        AuthorEntity authorEntity = TestDataUtils.createTestAuthor();
        BookEntity bookEntity = TestDataUtils.createTestBook(authorEntity);

        underTest.save(bookEntity);

        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(bookEntity);
    }

    @Test
    public void testThatMultipleBookCanBeCreatedAndRecalled() {
        AuthorEntity authorEntityA = TestDataUtils.createTestAuthorA();
        BookEntity bookEntityA = TestDataUtils.createTestBookWithAuthor(authorEntityA, "TestA");
        underTest.save(bookEntityA);

        BookEntity bookEntityAA = TestDataUtils.createTestBookWithAuthor(authorEntityA, "TestAA");
        underTest.save(bookEntityAA);

        AuthorEntity authorEntityB = TestDataUtils.createTestAuthorB();
        BookEntity bookEntityB = TestDataUtils.createTestBookWithAuthor(authorEntityB, "TestB");
        underTest.save(bookEntityB);

        Iterable<BookEntity> result = underTest.findAll();

        Assertions.assertThat(result)
                .hasSize(3)
                .containsExactly(bookEntityA, bookEntityAA, bookEntityB);
    }

    @Test
    public void testThatUpdateBook() {
        AuthorEntity authorEntity = TestDataUtils.createTestAuthorA();
        BookEntity bookEntity = TestDataUtils.createTestBookWithAuthor(authorEntity, "TestA");
        underTest.save(bookEntity);


        bookEntity.setTitle("AAA");

        underTest.save(bookEntity);

        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(bookEntity);
        Assertions.assertThat(result.get().getTitle()).isEqualTo("AAA");
    }

    @Test
    public void testThatDeleteBook() {
        AuthorEntity authorEntity = TestDataUtils.createTestAuthorA();
        BookEntity bookEntity = TestDataUtils.createTestBook(authorEntity);
        underTest.save(bookEntity);
        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());
        Assertions.assertThat(result).isPresent();

        underTest.delete(bookEntity);
        result = underTest.findById(bookEntity.getIsbn());
        Assertions.assertThat(result).isEmpty();
    }


}

