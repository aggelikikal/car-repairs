package com.teamnine.carrepairs.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(String message, Throwable t){
        super(message,t);
    }
}
