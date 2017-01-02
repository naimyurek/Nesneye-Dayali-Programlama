package com.hns.oop.exceptions;

public class QueryDatabaseException extends DatabaseException{

    public QueryDatabaseException() {
        super("Unsupported query format.");
    }
    
    public QueryDatabaseException(String s) {
        super(s);   
    }
    
}
