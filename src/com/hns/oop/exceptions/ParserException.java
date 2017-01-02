package com.hns.oop.exceptions;

public class ParserException extends Exception{

    public ParserException() {
        super("Unknown parser error.");
    }
    
    public ParserException(String str){
        super(str);
    }
}
