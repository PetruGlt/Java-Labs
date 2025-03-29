package com.example;

public class InvalidRepositoryException extends Exception {
    public InvalidRepositoryException(String message,Exception ex) {
        super(message, ex);
    }
}
