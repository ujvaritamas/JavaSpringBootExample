package com.example.PostgresJDBC;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.PostgresJDBC.mainpkg.Author;
import com.example.PostgresJDBC.mainpkg.Book;
import com.example.PostgresJDBC.repositories.AuthorRepository;
import com.example.PostgresJDBC.repositories.BookRepository;

@SpringBootApplication
public class PostgresJdbcApplication  implements CommandLineRunner{

	AuthorRepository authorRepo;
	BookRepository bookRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(PostgresJdbcApplication.class, args);
	}
	
	

	public PostgresJdbcApplication(AuthorRepository authorRepo, BookRepository bookRepo) {
		super();
		this.authorRepo = authorRepo;
		this.bookRepo = bookRepo;
	}



	@Override
	public void run(String... args) throws Exception {

			
	}

}
