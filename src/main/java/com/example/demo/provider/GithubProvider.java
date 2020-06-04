package com.example.demo.provider;

import com.alibaba.fastjson.JSON;
import com.example.demo.dto.AccessTokenDTO;
import com.example.demo.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
        try (Response response = client.newCall(request).execute()) {
                String string=response.body().string();
                //System.out.println(string);
                //access_token=dc4b9677d84a71dabba45a9aad9b52a3531e6b41&scope=user&token_type=bearer
                //通过方法获取token
                String token=string.split("&")[0].split("=")[1];
                return token;
            } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println(string);
            //{"login":"limoxiaon","id":64885834,"node_id":"MDQ6VXNlcjY0ODg1ODM0","avatar_url":"https://avatars3.githubusercontent.com/u/64885834?v=4"}
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
