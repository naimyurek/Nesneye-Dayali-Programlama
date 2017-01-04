package com.hns.oop.exam;

import com.hns.oop.exceptions.ParserException;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class ÖsymParser {
    
    private static ÖsymParser parser = null;
    private final String link;

    private ÖsymParser() {
        link = "http://www.osym.gov.tr/TR,8797/takvim.html";
    }
    
    public ArrayList<Exam> getList() throws ParserException{
        
        ArrayList<Exam> al = new ArrayList<>();
        
        try {
            Jsoup.connect(link).get().select("div.table > div.row").forEach((Element e) -> { // div.tablo'nun içindeki div.row'ların her biri için...
                String[] firstLast = e.select("div.col-sm-2").get(1).text().split(" ");
                String lastApp = (firstLast.length == 2) ? firstLast[1] : firstLast[2];
                al.add(new Exam(e.select("div.col-sm-6").first().text(), 
                                e.select("div.col-sm-2").get(0).text().split(" ")[0], 
                                e.select("div.col-sm-2").get(1).text().split(" ")[0],
                                lastApp, 
                                e.select("div.col-sm-2").get(2).text().split(" ")[0]));
            });
        } catch (IOException ex) {
            throw new ParserException("Connection problem.");
        }
        
        return al;
    }
    
    public static ÖsymParser getParser(){ // Singleton
        if (parser == null)
            parser = new ÖsymParser();
        return parser;
    }
}
