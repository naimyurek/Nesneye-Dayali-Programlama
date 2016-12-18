package com.hns.oop.article;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfFile {
   
    private final String Text ;
    private final File file;
    
    public PdfFile(String path) throws IOException{
        
        file = new File(path);
        PDFParser parser = new PDFParser(new RandomAccessFile(file,"r"));
        PDFTextStripper pdfStripper = new PDFTextStripper();

        parser.parse();
        PDDocument pdDoc = parser.getPDDocument();
        pdfStripper.setStartPage(1);
        pdfStripper.setEndPage(pdDoc.getNumberOfPages());
        
        Text = pdfStripper.getText(pdDoc);
        
    }
    
    @Override
    public String toString(){
        return Text;
    }
}
