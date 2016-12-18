package com.hns.oop;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Downloader {
    
    private static Downloader downloader;
    private String destination;

    private Downloader() {
        destination = "";
    }
    
    public static Downloader getDownloader(){
        if (downloader==null)
            downloader = new Downloader();
        return downloader;
    }
    
    public void download(String link, String name) throws IOException{
        URL url = new URL(link);
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(destination + name), StandardCopyOption.REPLACE_EXISTING);
        }
    }
    
    public void setDestination(String destination){
        this.destination = destination;
    }
    
    public String getDestination(){
        return destination;
    }
}
