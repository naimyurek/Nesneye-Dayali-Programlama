package com.hns.oop.article;

import com.hns.oop.exceptions.DatabaseException;
import java.util.ArrayList;
import org.junit.Test;

public class ArticleTest {
    
    @Test
    public void run(){
        Database<Article> db = new ArticleDatabase("mongodb://oop:658898@ds133398.mlab.com:33398/oop", "article");
        
        try {
            ArrayList<Article> al = db.find("");
            
            Article a1 = al.get(0);
            Article a2 = al.get(1);
            
            ArticleComparator ac = new ArticleComparator(new JaccardSimilarity3Gram());
            
            System.out.println("Keywordler a1:");
            System.out.println(a1.getKeywordsAsString(50));
            System.out.println("Keywordler a2:");
            System.out.println(a2.getKeywordsAsString(50));
            System.out.println();
            System.out.println("Similarity is: %" + String.format("%.2f", ac.getSimilarity(a1, a2)));
        } catch (DatabaseException ex) {
            System.out.println(ex);
        }
    }
}
