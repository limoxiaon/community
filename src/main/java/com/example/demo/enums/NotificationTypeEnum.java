package com.example.demo.enums;


public enum NotificationTypeEnum {
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(2,"回复了评论");

    private Integer Type;
    private String name;

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    NotificationTypeEnum(Integer type, String name) {
        Type = type;
        this.name = name;
    }

    public static String nameOfType(int type){
        for (NotificationTypeEnum notificationTypeEnum : NotificationTypeEnum.values()) {
            if(notificationTypeEnum.getType()==type){
                return notificationTypeEnum.getName();
            }
        }
        return "";
    }
}
