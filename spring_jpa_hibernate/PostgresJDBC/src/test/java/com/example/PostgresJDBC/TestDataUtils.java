package com.example.PostgresJDBC;

import com.example.PostgresJDBC.mainpkg.Author;
import com.example.PostgresJDBC.mainpkg.Book;
import com.example.PostgresJDBC.repositories.AuthorRepository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;

public final class TestDataUtils {

	private TestDataUtils() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static Author createTestAuthor() {
		return new Author(1L, "Test Name1", 32);
	}
	
	public static Author createTestAuthorA() {
		return new Author(3L, "Test NameA", 66);
	}
	
	public static Author createTestAuthorB() {
		return new Author(10L, "Test NameB", 55);
	}
	
	
	public static Book createTestBook(AuthorRepository authorRepo,Author author) {
		
		
		/* not needed @ManyToOne(cascade = CascadeType.ALL)
		if(!authorRepo.findById(author.getId()).isPresent()) {
			authorRepo.save(author);
		}
		*/

		
		return new Book("myISBN", "Test title1", author);
	}
	
public static Book createTestBookWithAuthor(AuthorRepository authorDao, Author author, String testIsbn) {
		
	/*// Not needed Hibernate handle it because of this -> @ManyToOne(cascade = CascadeType.ALL)
		if(!authorDao.findById(author.getId()).isPresent()) {
			authorDao.save(author);
		}
		else {
			
		}
		
		*/
		
		return new Book(testIsbn, "Test title1", author);
	}
	
	
}
