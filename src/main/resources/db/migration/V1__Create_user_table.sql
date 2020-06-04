CREATE TABLE USER  --用户表
(

    ID int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    ACCOUNT_ID VARCHAR(100),  --获取github用户ID的唯一标识
    NAME VARCHAR(50),         --用户名
    TOKEN VARCHAR(36),        --登录态，UUID生成
    GMT_CREATE BIGINT,        --创建时间
    GMT_MODIFIED BIGINT       --修改时间
);