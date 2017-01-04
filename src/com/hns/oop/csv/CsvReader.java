package com.hns.oop.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvReader {
    
    private final BufferedReader br;
    
    public CsvReader(String csvFile) throws FileNotFoundException, IOException {
        br = new BufferedReader(new FileReader(csvFile));
        readNext(); // İlk satırda column isimleri var. Onları es geç.
    }
    
    public String[] readNext() throws IOException{
        String line = br.readLine(); // Sonraki satırı okur.
        if (line == null)
            return null;
        return parse(line); // Virgüllere göre bölüp döndürür.
    }
    
    private String[] parse(String str){
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
                while(i != c.length && c[i]!=','){
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
    } // Satırı virgüllere göre Stringlere böler. Eğer yazı çift tırnak ile başlıyorsa çift tırnakları görmezden gelir.
}
