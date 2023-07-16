package com.apper.theblogservice.exceptions;

    public class emailAlreadyUsedException extends Exception {
        public emailAlreadyUsedException(String message) {
            super(message);
        }
    }

