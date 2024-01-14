package com.example.PostgresJDBC.mainpkg;

import java.util.Objects;

public class Book {
	private String isbn;
	
	private String title;
	
	private Long authorId;
	
	public Book(String isbn, String title, Long authorId) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.authorId = authorId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", authorId=" + authorId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorId, isbn, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(authorId, other.authorId) && Objects.equals(isbn, other.isbn)
				&& Objects.equals(title, other.title);
	}

	
}
