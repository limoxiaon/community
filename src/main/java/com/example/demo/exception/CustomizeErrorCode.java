package com.example.demo.exception;

public enum CustomizeErrorCode implements ErrorCode {
    Question_Not_Found("你找的问题不在，要不你换个试试？？");

    private String message;

    CustomizeErrorCode(String message) {
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
