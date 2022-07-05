package com.example.invoices.exception;

public class CustomerIsDeletedException extends RuntimeException {
    private String message;

    public CustomerIsDeletedException(String message) {
        super(message);
        this.message = message;
    }
    public CustomerIsDeletedException() {
    }
}