package com.example.PostgresJDBC.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.PostgresJDBC.mainpkg.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long>{

		Iterable<Author> ageLessThan(int age);
		
		@Query("SELECT a FROM Author a WHERE a.age > ?1")		//HQL example
		Iterable<Author> findAuthorsWithAgeGreatherThan(int age);
}
