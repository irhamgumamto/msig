package com.example.demo.exception.domain;

public class AppIdErrorException extends RuntimeException {
    public AppIdErrorException(String message) {
        super(message);
    }
    public AppIdErrorException() {
        this(null);
    }

}
