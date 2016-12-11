package com.hns.oop.exceptions;

public class InsertDatabaseException extends DatabaseException{

    public InsertDatabaseException() {
        super("Veritabanına veri girilirken hata.");
    }
    
    public InsertDatabaseException(String s) {
        super(s);
    }
    
}
