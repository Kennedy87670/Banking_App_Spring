package com.banking.bankingapp.exceptionHandlers;

public class InsufficientAccountBalance extends RuntimeException{
    public InsufficientAccountBalance() {
    }

    public InsufficientAccountBalance(String message) {
        super(message);
    }

    public InsufficientAccountBalance(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientAccountBalance(Throwable cause) {
        super(cause);
    }

    public InsufficientAccountBalance(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
