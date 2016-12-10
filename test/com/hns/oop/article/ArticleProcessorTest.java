/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hns.oop.article;

import com.hns.oop.exceptions.DatabaseException;
import java.io.IOException;
import org.junit.Test;

/**
 *
 * @author Harun
 */
public class ArticleProcessorTest {
    
    public ArticleProcessorTest() {
    }

    @Test
    public void testSet() {
        Database<Article> db = new ArticleDatabase("local","makale");
        try {
            ArticleProcessor.set(db,"acm.csv");
        }
        catch(IOException ex){
            System.out.println(ex);
        }
        
        try {
            db.find("").forEach(a -> {
               System.out.println(a);
            });
        }
        catch (DatabaseException ex){
            System.out.println(ex);
        }
    }
    
}
