package com.example.RestAPIProj.repositories;

import com.example.RestAPIProj.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
    Iterable<AuthorEntity> ageLessThan(int age);

    //@Query("SELECT a FROM Author a WHERE a.age > ?1")		//HQL example
    //Iterable<AuthorEntity> findAuthorsWithAgeGreatherThan(int age);
}
