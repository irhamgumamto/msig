package com.example.demo.exception.domain;

public class PermissionException extends RuntimeException {
    public PermissionException(String message) {
        super(message);
    }
}
