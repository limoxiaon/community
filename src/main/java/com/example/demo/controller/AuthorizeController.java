package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDTO;
import com.example.demo.dto.GithubUser;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.provider.GithubProvider;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@Slf4j
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String id;

    @Value("${github.client.secret}")
    private String secret;

    @Value("${github.client.redirect_url}")
    private String redirect_url;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    //授权访问
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code, @RequestParam(name="state") String state,
                           HttpServletResponse response){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(id);
        accessTokenDTO.setClient_secret(secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_url(redirect_url);
        accessTokenDTO.setState(state);

        //Post请求发送，获得accessToken
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);

        //获取对象
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser !=null){
            //登陆成功，跳转到自己的页面
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            String token = UUID.randomUUID().toString();
            user.setToken(token);

            //获取的用户信息，根据AccountId作为唯一标识判断数据库是否存在此用户，存在则储存用户信息，不存在则添加用户
            userService.createOrUpdate(user);

            //添加Cookie，token相当于登录态
            response.addCookie(new Cookie("token",token));

            return "redirect:/";
        }else{
            //登陆失败，跳转到登录界面
            log.error("get githubUser error");
            return "redirect:/";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){

        //清除User信息和登录态
        request.getSession().removeAttribute("user");

        //清除Cookie的一种办法
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
