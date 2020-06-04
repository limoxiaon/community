package com.example.demo.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.demo.exception.CustomizeErrorCode;
import com.example.demo.exception.CustomizeException;
import com.example.demo.utils.OSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class AliyunProvider {

    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.endpoint}")
    private String endpoint;

    @Value("${aliyun.bucketName}")
    private String bucketName;

    @Autowired
    private OSSClientUtil ossClientUtil;

    public String uploadPicture(MultipartFile file, String fileName){
        InputStream inputStream= null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(fileName);
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //上传文件流
        ossClient.putObject(bucketName, fileName, inputStream);

        //获取图片Url路径
        String imgUrl=ossClientUtil.checkImage(file);
        // 关闭OSSClient。
        ossClient.shutdown();

        return imgUrl;
    }

}
