package com.hns.oop.exceptions;

public class DatabaseException extends Exception {
    public DatabaseException(){
        super("Unknown database error.");
    }
    
    public DatabaseException(String s){
        super(s);
    }
}
