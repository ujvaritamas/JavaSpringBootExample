package com.example.PostgresJDBC.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.PostgresJDBC.mainpkg.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, String>{

}
