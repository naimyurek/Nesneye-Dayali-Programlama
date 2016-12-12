/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hns.oop.article;

import com.hns.oop.Initializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author Harun
 */
public class ArticleTest {
    
    public ArticleTest() {
        
    }
    
    @Test
    public void run(){
        Database<Article> db = new ArticleDatabase("local", "makale");
        try {
            Initializer.initArticle(db, "acm.csv");
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        try {
            ArrayList<Article> al = db.find("");
            
            Article a1 = al.get(0);
            Article a2 = al.get(1);
            
            System.out.println("Keywordler a1:");
            System.out.println(a1.getKeywords(50));
            System.out.println("Keywordler a2:");
            System.out.println(a2.getKeywords(50));
            System.out.println();
            System.out.println("Similarity is: %" + String.format("%.2f", a1.similarityTo(a2, 50)));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
