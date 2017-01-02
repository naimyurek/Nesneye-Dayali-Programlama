package com.hns.oop;

import com.hns.oop.csv.CsvWriter;
import com.hns.oop.csv.CsvReader;
import com.hns.oop.exam.Exam;
import com.hns.oop.exam.ÖsymParser;
import com.hns.oop.exceptions.ParserException;
import java.io.IOException;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;

public class CsvWriterTest {
    private CsvWriter cw;
    
    @Before
    public void before() {
        String[] columns = {"ad","tarih"};
        try {
            cw = new CsvWriter("exams.csv", columns);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Test
    public void testWrite() throws IOException, ParserException{
        System.out.println("write");
        for(Exam e : ÖsymParser.getParser().getList()){
            String[] contents = {e.getAd(), e.getTarih()};
            cw.write(contents);
        }
        cw.close();
        System.out.println("Written");
    }
    
    @After
    public void read() throws IOException{
        CsvReader cr = new CsvReader("exams.csv");
        String[] ss;
        cr.readNext();
        while((ss = cr.readNext())!= null){
            System.out.println(ss[0]+","+ss[1]);
        }
    }
    
}
