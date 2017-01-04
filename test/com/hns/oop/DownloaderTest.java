package com.hns.oop;

import org.junit.Test;

public class DownloaderTest {
    
    @Test
    public void testDownload() throws Exception {
        
        Downloader instance = Downloader.getDownloader();
        String link = "http://img-9gag-fun.9cache.com/photo/aG0rdD5_700b.jpg";
        String name = "image.jpg";
        instance.download(link, name);
        System.out.println("Downloaded.");
    }
    
}
