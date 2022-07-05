package com.example.invoices.exception;

public class ObjectNotAddedException extends RuntimeException {
    private String message;

    public ObjectNotAddedException(String message) {
        super(message);
        this.message = message;
    }

    public ObjectNotAddedException() {
    }
}