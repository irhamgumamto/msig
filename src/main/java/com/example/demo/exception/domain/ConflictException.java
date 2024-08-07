package com.example.demo.exception.domain;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
