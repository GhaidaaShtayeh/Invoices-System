package com.example.invoices.exception;

public class ItemIsDeletedException extends RuntimeException {
    private String message;

    public ItemIsDeletedException(String message) {
        super(message);
        this.message = message;
    }
    public ItemIsDeletedException() {
    }
}
