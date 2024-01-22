package com.example.RestAPIProj.repositories;

import com.example.RestAPIProj.TestDataUtils;
import com.example.RestAPIProj.domain.entities.AuthorEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Optional;

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
        AuthorEntity authorEntity = TestDataUtils.createTestAuthor();
        underTest.save(authorEntity);
        Optional<AuthorEntity> result = underTest.findById(authorEntity.getId());

        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(authorEntity);
    }

    @Test
    public void testThatMultipleAuthorCanBeCreatedAndRecalled() {
        AuthorEntity authorEntityA = TestDataUtils.createTestAuthorA();
        underTest.save(authorEntityA);
        AuthorEntity authorEntityB = TestDataUtils.createTestAuthorB();
        underTest.save(authorEntityB);

        Iterable<AuthorEntity> result = underTest.findAll();
        Assertions.assertThat(result)
                .hasSize(2)
                .containsExactly(authorEntityA, authorEntityB);
    }

    @Test
    public void testThatMultipleAuthorCanBeCreatedAndRecalled1() {
        AuthorEntity authorEntityA = TestDataUtils.createTestAuthorA();
        underTest.save(authorEntityA);
        AuthorEntity authorEntityB = TestDataUtils.createTestAuthorB();
        underTest.save(authorEntityB);


        Iterable<AuthorEntity> result = underTest.findAll();
        Assertions.assertThat(result)
                .hasSize(2)
                .containsExactly(authorEntityA, authorEntityB);
    }

    @Test
    public void testThatUpdateAuthor() {
        AuthorEntity authorEntity = TestDataUtils.createTestAuthor();
        underTest.save(authorEntity);

        authorEntity.setAge(10);

        underTest.save(authorEntity);

        Optional<AuthorEntity> result = underTest.findById(authorEntity.getId());
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(authorEntity);

    }

    @Test
    public void testThatDeleteAuthor() {
        AuthorEntity authorEntity = TestDataUtils.createTestAuthor();

        underTest.save(authorEntity);

        Optional<AuthorEntity> result = underTest.findById(authorEntity.getId());
        Assertions.assertThat(result).isPresent();

        underTest.delete(authorEntity);
        result = underTest.findById(authorEntity.getId());
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan() {
        AuthorEntity authorEntity0 = new AuthorEntity(1L, "Arhur0", 32);
//		underTest.save(author0);
        AuthorEntity authorEntity1 = new AuthorEntity(2L, "Arhur1", 88);
//		underTest.save(author1);
        AuthorEntity authorEntity2 = new AuthorEntity(3L, "Arhur2", 35);
//		underTest.save(author2);


        AuthorEntity[] authorEntities = new AuthorEntity[]{
                authorEntity0,
                authorEntity1,
                authorEntity2
        };

        underTest.saveAll(Arrays.asList(authorEntities));

        Iterable<AuthorEntity> result = underTest.ageLessThan(50);
        Assertions.assertThat(result)
                .hasSize(2)
                .contains(authorEntity0)
                .contains(authorEntity2);
    }

    /*@Test
    public void testThatGetAuthorsWithAgeGreatherThan() {
        AuthorEntity authorEntity0 = new AuthorEntity(1L, "Arhur0", 32);
        AuthorEntity authorEntity1 = new AuthorEntity(2L, "Arhur1", 88);
        AuthorEntity authorEntity2 = new AuthorEntity(3L, "Arhur2", 35);


        AuthorEntity[] authorEntities = new AuthorEntity[]{
                authorEntity0,
                authorEntity1,
                authorEntity2
        };

        underTest.saveAll(Arrays.asList(authorEntities));

        Iterable<AuthorEntity> result = underTest.findAuthorsWithAgeGreatherThan(50);

        Assertions.assertThat(result)
                .hasSize(1)
                .contains(authorEntity1);
    }*/
}
