package com.hns.oop.csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {
    
    private BufferedWriter bw;
    private FileWriter fw;
    
    public CsvWriter(String fileName, String[] columns) throws IOException{
        bw = null;
        fw = null;
        if (columns.length == 0){
            throw new IOException("There should be a least one column name.");
        }
        
        try {
            fw = new FileWriter(fileName);
            bw = new BufferedWriter(fw);
            write(columns);
	} 
        catch (IOException e) {
            if (bw != null)
                 bw.close();

            if (fw != null)
                fw.close();
            throw e;
	} 
    }
    
    public void write(String[] contents) throws IOException{
        String s = "";
        if (contents.length == 0){
            bw.newLine();
        }
        else{
            s += contents[0];
            for(int i=1; i<contents.length;i++){
                s += ","+contents[i];
            }
            s += '\n';
            bw.write(s);
        }
    }
    
    public void close() throws IOException{
        if (bw != null)
            bw.close();

	if (fw != null)
            fw.close();
    }
}
