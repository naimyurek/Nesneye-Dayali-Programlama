package com.hns.oop;

import com.hns.oop.article.Article;
import com.hns.oop.article.CsvReader;
import com.hns.oop.article.Database;
import com.hns.oop.article.PdfFile;
import com.hns.oop.exceptions.DatabaseException;
import java.io.IOException;

public abstract class Initializer {
    
    public static void populateDatabase(Database<Article> db, String csvFile) throws IOException {
        
        CsvReader cr = new CsvReader(csvFile);
        String[] s;
        Downloader d = Downloader.getDownloader();
        d.setDestination("");

        while ((s = cr.next())!= null) {
            
            d.download(s[5], s[0] + ".pdf");
            
            PdfFile pdfFile = new PdfFile(d.getDestination() + s[0] + ".pdf");
            
            Article article = new Article(s[0], s[1], s[2], s[3], s[4], pdfFile.toString());
            
            try{
                db.insert(article);
            }
            catch (DatabaseException ex){}
        }
    }
}