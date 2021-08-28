package com.thecommunity.exceptions;

public class EmailExistException extends RuntimeException{

    public EmailExistException(){

    }
    public EmailExistException(String message){
        super(message);
    }

    public EmailExistException(String message, Throwable throwable){
        super(message, throwable);
    }
    public EmailExistException(Throwable throwable){
        super(throwable);
    }
}
