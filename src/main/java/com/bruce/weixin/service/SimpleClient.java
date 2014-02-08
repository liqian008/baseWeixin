package com.bruce.weixin.service;

import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

import com.bruce.weixin.bean.WeatherWrapper;
import com.google.gson.Gson;

/**
 * 最简单的HTTP客户端,用来演示通过GET或者POST方式访问某个页面
 * 
 * @author Liudong
 */
public class SimpleClient {
    
    public static String httpGet(String url) throws IOException {
        
        HttpClient client = new HttpClient();
        // 设置代理服务器地址和端口
        // client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
        // 使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
        HttpMethod method = new GetMethod(url);
        // 使用POST方法
        // HttpMethod method = new PostMethod("http://java.sun.com";);
        client.executeMethod(method);
        // 打印服务器返回的状态
        System.err.println(method.getStatusLine());
        // 打印返回的信息
        String responseString = method.getResponseBodyAsString();
        System.out.println(responseString);
        // 释放连接
        method.releaseConnection();
        return responseString;
    }
    
    
    public static void main(String[] args) throws IOException {
        String weatherJsonStr = httpGet("http://m.weather.com.cn/data/101010100.html");
        Gson gson = new Gson();
        WeatherWrapper weatherBean = gson.fromJson(weatherJsonStr, WeatherWrapper.class);
        System.out.println(weatherBean);
    }
    
    
    
}