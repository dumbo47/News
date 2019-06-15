package com.example.news.model;

public class Article {
    
    private String title;
    int img;
    private String author;
    private String description;
    private String url;
    
    public Article(String title, String author, String description ,String url) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.url = url;
        
    }
    
    public String getUrl() {
        return url;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setImg(int img) {
        this.img = img;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getImg() {
        return img;
    }
    
    
}
