package com.hns.oop;

import com.hns.oop.exceptions.ParserException;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public abstract class ÖsymParser {
 
    public static final String link = "http://www.osym.gov.tr/TR,8797/takvim.html";
    
    public static ArrayList<Sınav> getSınavListesi() throws ParserException{
        
        ArrayList<Sınav> al = new ArrayList<>();
        
        try {
            Jsoup.connect(link).get().select("div.table > div.row").forEach((Element e) -> {
                al.add(new Sınav(e.select("div.col-sm-6").first().text(), e.select("div.col-sm-2").first().text()));
            });
        } catch (IOException ex) {
            throw new ParserException("Siteye erişimde sorun var");
        }
        
        return al;
    }
}
