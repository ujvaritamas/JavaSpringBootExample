package com.example.PostgresJDBC.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.PostgresJDBC.mainpkg.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long>{

}
