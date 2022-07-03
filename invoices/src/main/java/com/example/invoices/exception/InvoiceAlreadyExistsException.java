package com.example.invoices.exception;

public class InvoiceAlreadyExistsException  extends RuntimeException {
    private String message;

    public InvoiceAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
    public InvoiceAlreadyExistsException() {
    }
}