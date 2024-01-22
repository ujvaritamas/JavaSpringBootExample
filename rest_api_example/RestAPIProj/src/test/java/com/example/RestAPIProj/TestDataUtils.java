package com.example.RestAPIProj;

import com.example.RestAPIProj.domain.dto.AuthorDto;
import com.example.RestAPIProj.domain.dto.BookDto;
import com.example.RestAPIProj.domain.entities.AuthorEntity;
import com.example.RestAPIProj.domain.entities.BookEntity;
import org.springframework.test.annotation.DirtiesContext;

public class TestDataUtils {

    private TestDataUtils() {
        super();
        // TODO Auto-generated constructor stub
    }

    public static AuthorEntity createTestAuthor() {
        return new AuthorEntity(1L, "Test Name1", 32);
    }

    public static AuthorEntity createTestAuthorA() {
        return new AuthorEntity(3L, "Test NameA", 66);
    }

    public static AuthorEntity createTestAuthorB() {
        return new AuthorEntity(10L, "Test NameB", 55);
    }


    public static BookEntity createTestBook(AuthorEntity author) {


		/* not needed @ManyToOne(cascade = CascadeType.ALL)
		if(!authorRepo.findById(author.getId()).isPresent()) {
			authorRepo.save(author);
		}
		*/


        return new BookEntity("myISBN", "Test title1", author);
    }

    public static BookDto createTestBookDto(AuthorDto author) {


		/* not needed @ManyToOne(cascade = CascadeType.ALL)
		if(!authorRepo.findById(author.getId()).isPresent()) {
			authorRepo.save(author);
		}
		*/


        return new BookDto("myISBN", "Test title1", author);
    }

    public static BookEntity createTestBookWithAuthor(AuthorEntity author, String testIsbn) {

	/*// Not needed Hibernate handle it because of this -> @ManyToOne(cascade = CascadeType.ALL)
		if(!authorDao.findById(author.getId()).isPresent()) {
			authorDao.save(author);
		}
		else {

		}

		*/

        return new BookEntity(testIsbn, "Test title1", author);
    }


}
