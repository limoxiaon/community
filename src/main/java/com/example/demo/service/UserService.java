package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dBUser=userMapper.findByAccountId(user.getAccountId());
        if(dBUser == null){
            //执行插入操作
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            //执行更新操作
            dBUser.setName(user.getName());
            dBUser.setAvatarUrl(user.getAvatarUrl());
            dBUser.setToken(user.getToken());
            dBUser.setGmtModified(System.currentTimeMillis());
            userMapper.update(dBUser);
        }
    }
}
