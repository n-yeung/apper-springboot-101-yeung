package com.apper.theblogservice.exceptions;

public class blogNotFoundException extends Exception{
    public blogNotFoundException(String message){
        super(message);
    }
}
