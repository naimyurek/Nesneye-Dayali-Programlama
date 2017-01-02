package com.hns.oop.article;

import com.hns.oop.exceptions.DatabaseException;
import com.hns.oop.exceptions.InsertDatabaseException;
import com.hns.oop.exceptions.QueryDatabaseException;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.function.Consumer;
import org.bson.Document;

public class ArticleDatabase implements Database{

    private MongoClient mongo;
    private MongoDatabase db;
    private MongoCollection table;
    
    public ArticleDatabase(String host, String collectionName){
        
        MongoClientURI uri  = new MongoClientURI(host);
        mongo = new MongoClient( uri );
        db = mongo.getDatabase(uri.getDatabase());
        table = db.getCollection(collectionName);
    }

    @Override
    public void insert(Object object) throws DatabaseException{
        
        Article makale;
        if (object instanceof Article)
            makale = (Article) object;
        else
            throw new DatabaseException("The object is not 'Article'.");
        
        if (find("id=" + makale.getId()).size()>0)
            throw new InsertDatabaseException("This record already exists in the database.");
        else{
            Document document = new Document();
        
            document.put("id", ""+makale.getId());
            document.put("title", makale.getTitle());
            document.put("author", makale.getAuthor());
            document.put("venue", makale.getVenue());
            document.put("year", makale.getYear());
            document.put("content", makale.getContent());
            document.put("keywords", makale.getKeywordsAsString(50));

            table.insertOne(document);
        }
    } // Parametre olarak Makale nesnesi alır ve tabloda bu makale yoksa ekler, varsa Exception döndürür.

    @Override
    public ArrayList<Article> find(String condition) throws QueryDatabaseException{
        
        ArrayList<Article> al = new ArrayList<>();
        
        table.find(getQuery(condition)).forEach(new Consumer() {
            @Override
            public void accept(Object t) {
                Document d = (Document) t;
                Article m = new Article(d.getString("id"), d.getString("title"), d.getString("author"), 
                                      d.getString("venue"), d.getString("year"), d.getString("content"));
                al.add(m);
            }
        });
        return al;
    } // String olarak bir komut alır ve tablodan bu kısıta uygun girdileri bulur, makale nesnesine dönüştürür ve bütün makalelerin listesini döndürür.
    
    private BasicDBObject getQuery(String s) throws QueryDatabaseException{
        BasicDBObject q = new BasicDBObject();
        
        if(s.isEmpty())
            return q;
        
        String cs[] = s.split(",");
        
        for(String c : cs){
            String i[] = c.split("=");
            if(i.length != 2)
                throw new QueryDatabaseException();
            if(i[1].charAt(0)=='/'){
                if(i[1].length()<=2)
                    throw new QueryDatabaseException();
                String r = i[1].substring(1, i[1].length()-1);
                q.put(i[0], new BasicDBObject("$regex", r).append("$options","i"));
            }
            else
                q.put(i[0],i[1]);
        }
        
        return q;
    } // String olarak aldığı komutu BasicDBObject'e çevirerek döndürür. Örnek stringler: "id=1" | "id=1,title=x" vs. && Yanlışlar: "id" | "id=" | "id=1,title" vs.
}
