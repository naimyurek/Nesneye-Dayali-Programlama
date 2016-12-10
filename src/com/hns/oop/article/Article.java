package com.hns.oop.article;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Article {
    
    private String id;
    private String title;
    private String author;
    private String venue;
    private String year;
    private String content;
    
    public Article(String id, String title, String author, String venue, String year, String content){
        this.id = id;
        this.title = title;
        this.author = author;
        this.venue = venue;
        this.year = year;
        this.content = content;
    }
    
    public void setYear(String year) {
        this.year = year;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public String getVenue() {
        return venue;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return title;
    }
    
    public String getKeywords(){
        return null;
    }
    
    public ArrayList<String> getAnagramList(){
        return null;
    }
    
    public ArrayList<String> getKeywordList(){
        return null;
    }
    
    public float similarityTo(Article m){
        Set<String> set1 = new HashSet<String>(getAnagramList());
        Set<String> set2 = new HashSet<String>(m.getAnagramList());
        
        int size1 = set1.size();
        int size2 = set2.size();
        
        set1.retainAll(set2);
        return (float)100*(set1.size()/(size1 + size2 - set1.size()));
    }
}
