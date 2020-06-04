package com.example.demo.dto;

import lombok.Data;

//获得accessToken的参数封装
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_url;
    private String state;

}
