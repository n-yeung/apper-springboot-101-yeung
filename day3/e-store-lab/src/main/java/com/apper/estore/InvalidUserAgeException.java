package com.apper.estore;

public class InvalidUserAgeException extends RuntimeException {
    public InvalidUserAgeException(String message) {
        super(message); //prints message in postman
        //super calls runtimeException
        System.out.println(message); //prints message in terminal
    }
}