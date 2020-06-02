package com.example.demo.exception;

public enum CustomizeErrorCode implements ErrorCode {
    QUESTION_NOT_FOUND(2001,"你找的问题不在，要不你换个试试？？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NOT_LOGIN(2003,"未登录，请进行登录操作"),
    SYS_ERROR(2004,"服务器要炸掉了，要不等等重试？？"),
    TYPE_PARAM_WRONG(2005,"类型不存在"),
    COMMENT_NOT_FOUND(2006,"评论找不到"),
    COMMENT_IS_NULL(2007,"评论为空"),
    READ_NOTIFICATION_FAIL(2008,"你莫不是读取到了别人的信息？"),
    NOTIFICATION_NOT_FOUND(2009,"通知难道不翼而飞了？");


    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
