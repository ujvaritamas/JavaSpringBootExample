package com.example.emailsecexample.model;

public class Book {

    private Long id;
    private String title;
    private String authorName;

    public Book(){

    }

    public Book(Long id, String title, String authorName) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
    }

    public Long getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthorName(){
        return this.authorName;
    }

    public void setTitle(String title){
        this.title = title;
    }
    
    public void setAuthorName(String authorName){
        this.authorName = authorName;
    }

    public void setId(Long id){
        this.id = id;
    }



    
}
