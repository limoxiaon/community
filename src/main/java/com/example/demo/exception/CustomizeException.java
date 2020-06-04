package com.example.demo.exception;

public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;

    //给异常类传递异常信息和异常代码
    public CustomizeException(ErrorCode error) {
        this.code=error.getCode();
        this.message=error.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
