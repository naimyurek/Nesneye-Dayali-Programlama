package com.hns.oop.exceptions;

public class NotifierException extends Exception{
    
    public NotifierException(){
        super("An error occured while notifying.");
    }
    
    public NotifierException(String str){
        super(str);
    }
    
}
