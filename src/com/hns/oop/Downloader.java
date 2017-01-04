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

    private Downloader() { }
    
    public static Downloader getDownloader(){ // Singleton design pattern
        if (downloader==null)
            downloader = new Downloader();
        return downloader;
    }
    
    public void download(String link, String name) throws IOException{ // Bir linki "name" olarak program dizinine indirir.
        
        URL url = new URL(link);
        InputStream in = url.openStream();
        Files.copy(in, Paths.get(name), StandardCopyOption.REPLACE_EXISTING);
    }
}
