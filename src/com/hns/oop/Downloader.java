package com.hns.oop;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Downloader {
    
    public static void download(String link, String name) throws IOException{
        URL url = new URL(link);
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(name), StandardCopyOption.REPLACE_EXISTING);
        }
    }
    
}
