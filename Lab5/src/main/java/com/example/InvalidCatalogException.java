package com.example;

public class InvalidCatalogException extends Exception {
    public InvalidCatalogException(String message, Exception ex) {
        super(message, ex);
    }
}
