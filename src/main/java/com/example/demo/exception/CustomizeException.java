package com.example.demo.exception;

public class CustomizeException extends RuntimeException {
    private String message;

    public CustomizeException(ErrorCode error) {
        this.message=error.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
