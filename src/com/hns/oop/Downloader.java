package com.hns.oop;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Downloader {
    
    private static Downloader downloader;
    private String directory;

    private Downloader() {
        directory = "";
    }
    
    public static Downloader getDownloader(){
        if (downloader==null)
            downloader = new Downloader();
        return downloader;
    }
    
    public void download(String link, String name) throws IOException{    
        
        URL url = new URL(link);
        InputStream in = url.openStream();
        
        if (directory.length()!=0){
            File file = new File(directory);
            if (!file.exists())
                file.mkdir();
        }

        Files.copy(in, Paths.get(directory + name), StandardCopyOption.REPLACE_EXISTING);
    }
    
    public void setDirectory(String directory){
        this.directory = directory;
    }
    
    public String getDirectory(){
        return directory + "/";
    }
}
