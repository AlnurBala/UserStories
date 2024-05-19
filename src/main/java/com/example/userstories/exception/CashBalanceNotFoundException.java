package com.example.userstories.exception;

public class CashBalanceNotFoundException extends RuntimeException {
    public CashBalanceNotFoundException(String message) {
        super(message);
    }
}
