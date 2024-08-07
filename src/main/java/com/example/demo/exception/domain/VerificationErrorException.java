package com.example.demo.exception.domain;

public class VerificationErrorException extends RuntimeException {
    public VerificationErrorException(String message) {
        super(message);
    }
}
