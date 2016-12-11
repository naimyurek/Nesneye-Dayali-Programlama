/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hns.oop.exceptions;

/**
 *
 * @author Harun
 */
public class QueryDatabaseException extends DatabaseException{

    public QueryDatabaseException() {
        super("Sorguda hata var.");
    }
    
    public QueryDatabaseException(String s) {
        super(s);   
    }
    
}
