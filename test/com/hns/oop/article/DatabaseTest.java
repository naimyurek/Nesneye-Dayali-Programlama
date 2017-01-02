package com.hns.oop.article;

import com.hns.oop.exceptions.DatabaseException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;

public class DatabaseTest {
    Database<Article> db;
    
    @Before
    public void setUp() {
        db = new ArticleDatabase("mongodb://oop:658898@ds133398.mlab.com:33398/oop" , "articleDeneme");
    }
    
    @Test
    public void testInsert() {
        try {
            db.insert(new Article("3","Title","Hans Brucher","abc","2009","content"));
        } catch (DatabaseException ex) {
            System.out.println(ex);
        }
    }
    
    @After
    public void testFind() {
        try {
            ArrayList<Article> al = db.find("");
            System.out.println("List Start");
            al.forEach(System.out::println);
            System.out.println("List Finish");
        } catch (DatabaseException ex) {
            System.out.println(ex);
        }
    }
}
