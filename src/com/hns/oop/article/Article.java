package com.hns.oop.article;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    
    @Override
    public boolean equals(Object o){
        if (o instanceof Article){
            Article a = (Article) o;
            return getId().equals(a.getId());
        }
        return false;
    }
    
    public String getKeywordsAsString(int length){ // Tüm keywordleri aralarına boşluk koyarak String'e çevirip döndürür.
        String str = "";
        
        str = getKeywordList(length).stream().map((x) -> x + " ").reduce(str, String::concat);
            
        return str;
    }
    
    public List<String> getKeywordList(int length){
        String[] s = content.split("\\W+"); // İçerikten sadece kelime olanları bir diziye aktarir.
        
        Map<String, Integer> map = new HashMap<>(); // (Kelime, Tekrar Sayısı) olacak şekilde bir HashMap.
        
        for (String w : s) {
            
            w = w.toLowerCase();
            Integer n = map.get(w); // O kelimenin tekrar sayısını bulur.
            n = (n == null) ? 1 : ++n; // O kelime yoksa tekrar sayısını 1 yapar, varsa 1 arttırır.
            map.put(w, n); // Map'e geri koyar.
            
        }
        
        List<String> l = map.entrySet() // HashMap'i Tekrar sayısına göre azalan şekilde sıralar. Daha sonra sadece kelimeleri listeye çevirir.
        .stream()
        .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new))
        .keySet().stream().collect(Collectors.toList());
        
        if(length > l.size() | length == 0)
            return l;
        else
            return l.subList(0, length);
    } // En çok tekrar eden "length" adet kelimenin listesini döndürür.
    
}
