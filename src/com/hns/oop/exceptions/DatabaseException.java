package com.hns.oop.exceptions;

public class DatabaseException extends Exception {
    public DatabaseException(){
        super("Bilinmeyen database hatası.");
    }
    
    public DatabaseException(String s){
        super(s);
    }
}
