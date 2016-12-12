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
public class EntryNotFoundDatabaseException extends DatabaseException{

    public EntryNotFoundDatabaseException() {
        super("Kayıt bulunamadı.");
    }
    
}
