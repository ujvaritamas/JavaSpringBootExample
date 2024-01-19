package com.example.demoWeb.domain;

import lombok.Data;

@Data
public class Book {
	private String isbn;
	
	private String title;
	
	private String author;
	
	private String yearPublished;
	
	

	public Book() {
		super();
	}

	public Book(String isbn, String title, String author, String yearPublished) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.yearPublished = yearPublished;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public String getYearPublished() {
		return yearPublished;
	}

	public void setYearPublished(String yearPublished) {
		this.yearPublished = yearPublished;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", yearPublished=" + yearPublished
				+ "]";
	}
	
	
}
