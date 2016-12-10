package com.hns.oop.article;

import com.hns.oop.article.Database;
import com.hns.oop.exceptions.DatabaseException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public abstract class ArticleProcessor {
    
    public static void set(Database<Article> db, String csvFile) throws IOException {
        String line;

        BufferedReader br = new BufferedReader(new FileReader(csvFile));

        while ((line = br.readLine())!= null) {
            String[] s = parse(line);
            
            downloadPdf(s[5], s[0]);
            
            Article article = new Article(s[0], s[1], s[2], s[3], s[4], getContent(s[0]));
            try{
                db.insert(article);
            }
            catch (DatabaseException ex){}
        }
    }
    
    private static String[] parse(String str){
        ArrayList<String> al = new ArrayList();
        
        char[] c = str.toCharArray();
        int i = 0;
        
        while(true){
            StringBuilder sb = new StringBuilder();
            if(c[i] == '\"'){
                i++;
                while(c[i]!='\"'){
                    sb.append(c[i]);
                    i++;
                }
                i++;
            }
            else{
                while(c[i]!=',' && c[i]!='\0'){
                    sb.append(c[i]);
                    i++;
                }
            }
            al.add(sb.toString());
            if(i==c.length)
                break;
            i++;
        }
        
        return al.toArray(new String[0]);
    }
    
    private static String getContent(String id) throws IOException {
        return (new PdfFile(id + ".pdf")).toString();
    }
    
    private static void downloadPdf(String link, String name) throws IOException{
        URL url = new URL(link);
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(name + ".pdf"), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
