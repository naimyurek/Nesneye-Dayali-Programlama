package com.hns.oop.exceptions;

public class InsertDatabaseException extends DatabaseException{

    public InsertDatabaseException() {
        super("An error occured while inserting the record.");
    }
    
    public InsertDatabaseException(String s) {
        super(s);
    }
    
}
