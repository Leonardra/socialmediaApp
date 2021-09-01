package com.thecommunity.exceptions;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException(){
        super();
    }
    public InvalidPasswordException(String message){
        super(message);
    }

    public InvalidPasswordException(Throwable throwable){
        super(throwable);
    }

    public InvalidPasswordException(String message, Throwable throwable){
        super(message, throwable);
    }
}
