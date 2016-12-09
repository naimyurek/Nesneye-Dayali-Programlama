package com.hns.oop.database;

import com.hns.oop.Makale;
import com.hns.oop.exceptions.DatabaseException;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.function.Consumer;
import org.bson.Document;

public class MakaleDatabase implements Database{

    private MongoClient mongo;
    private MongoDatabase db;
    private MongoCollection table;
    
    public MakaleDatabase(String dbName, String collectionName){
        
        mongo = new MongoClient( "localhost" , 27017 );
        db = mongo.getDatabase(dbName);
        table = db.getCollection(collectionName);
    }

    @Override
    public void insert(Object object) throws DatabaseException{
        Makale makale = (Makale) object;
        
        if(find("id=" + makale.getId()).isEmpty()){
            Document document = new Document();
        
            document.put("id", ""+makale.getId());
            document.put("title", makale.getTitle());
            document.put("author", makale.getAuthor());
            document.put("venue", makale.getVenue());
            document.put("year", makale.getYear());
            document.put("content", makale.getContent());
            document.put("keywords", makale.getKeywords());
        
            table.insertOne(document);
        }
        else{
            throw new DatabaseException("Bu makale zaten mevcut.");
        }
    } // Parametre olarak Makale nesnesi alır ve tabloda bu makale yoksa ekler, varsa Exception döndürür.

    @Override
    public ArrayList<Makale> find(String condition) throws DatabaseException{
        
        ArrayList<Makale> al = new ArrayList<>();
        
        table.find(getQuery(condition)).forEach(new Consumer() {
            @Override
            public void accept(Object t) {
                Document d = (Document) t;
                Makale m = new Makale(d.getString("id"), d.getString("title"), d.getString("author"), 
                                      d.getString("venue"), d.getString("author"), d.getString("content"));
                al.add(m);
            }
        });
        return al;
    } // String olarak bir komut alır ve tablodan bu kısıta uygun girdileri bulur, makale nesnesine dönüştürür ve bütün makalelerin listesini döndürür.
    
    private BasicDBObject getQuery(String s) throws DatabaseException{
        BasicDBObject q = new BasicDBObject();
        
        if(s.isEmpty())
            return q;
        
        String cs[] = s.split(",");
        
        for(String c : cs){
            String i[] = c.split("=");
            if(i.length != 2)
                throw new DatabaseException("Sorguda hata var.");
            q.put(i[0],i[1]);
        }
        
        return q;
    } // String olarak aldığı komutu BasicDBObject'e çevirerek döndürür. Örnek stringler: "id=1" | "id=1,title=x" vs. && Yanlışlar: "id" | "id=" | "id=1,title" vs.
}
