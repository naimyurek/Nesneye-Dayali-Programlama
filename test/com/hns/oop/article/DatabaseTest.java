package com.hns.oop.article;

import com.hns.oop.exceptions.DatabaseException;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.Before;

public class DatabaseTest {
    Database<Article> db;
    
    @Before
    public void setUp() {
        db = new ArticleDatabase("local", "makale");
    }
    
    @Test
    public void testInsert() {
        try {
            db.insert(new Article("3","Title","Hans Brucher","abc","2009","content"));
            System.out.println("Eklendi");
        } catch (DatabaseException ex) {
            System.out.println(ex);
        }
    }
    
    @Test
    public void testFind() {
        try {
            ArrayList<Article> al = db.find("year=2009");
            System.out.println("List Start");
            al.forEach(System.out::println);
            System.out.println("List Finish");
        } catch (DatabaseException ex) {
            System.out.println(ex);
        }
    }
}