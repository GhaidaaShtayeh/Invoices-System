package com.example.invoices.exception;

public class InvoiceIsDeletedException extends RuntimeException {
    private String message;

    public InvoiceIsDeletedException(String message) {
        super(message);
        this.message = message;
    }
    public InvoiceIsDeletedException() {
    }
}