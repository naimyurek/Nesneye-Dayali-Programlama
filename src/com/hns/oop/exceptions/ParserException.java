package com.hns.oop.exceptions;

public class ParserException extends Exception{

    public ParserException() {
        super("Bilinmeyen parser hatası.");
    }
    
    public ParserException(String str){
        super(str);
    }
}
