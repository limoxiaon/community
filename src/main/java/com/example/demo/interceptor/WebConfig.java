package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//自定义的Web配置，实现了WebMvcConfigurer和@EnableWebMvc，则默认配置失效
//此时可以添加拦截器
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    SessionInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler(url请求的路径)，addResourceLocations(服务器真实路径)
        registry.addResourceHandler("/**")
                .addResourceLocations("/public", "classpath:/static/")
                .setCachePeriod(31556926);
    }
}
