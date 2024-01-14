package com.learning.url.exception;

public class EntitiesNotFoundException extends RuntimeException{
    public EntitiesNotFoundException(String message) {
        super(message);
    }
}
