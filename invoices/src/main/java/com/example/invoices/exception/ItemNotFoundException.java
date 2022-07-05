package com.example.invoices.exception;

public class ItemNotFoundException extends RuntimeException {
    private String message;

    public ItemNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    public ItemNotFoundException() {
    }
}