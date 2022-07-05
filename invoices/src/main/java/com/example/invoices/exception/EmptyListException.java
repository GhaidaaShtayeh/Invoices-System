package com.example.invoices.exception;

public class EmptyListException extends RuntimeException {
    private String message;

    public EmptyListException(String message) {
        super(message);
        this.message = message;
    }
    public EmptyListException() {
    }
}