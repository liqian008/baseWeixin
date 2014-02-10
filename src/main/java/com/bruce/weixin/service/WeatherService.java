package com.bruce.weixin.service;

import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

import com.bruce.weixin.bean.WeatherInfo;
import com.bruce.weixin.bean.WeatherWrapper;
import com.google.gson.Gson;

public class WeatherService {
    

    private static final String CITY_CODE_DALI = "101290201";
    
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
//        System.err.println(method.getStatusLine());
        // 打印返回的信息
        String responseString = method.getResponseBodyAsString();
        // 释放连接
        method.releaseConnection();
        return responseString;
    }
    
    public static String getDaliWeather() throws IOException{
       return getWeatherByCode(CITY_CODE_DALI);
    }
    
    public static String getWeatherByCode(String cityCode) throws IOException{
        String weatherJsonStr = httpGet("http://m.weather.com.cn/data/"+cityCode+".html");
        Gson gson = new Gson();
        WeatherWrapper weatherBean = gson.fromJson(weatherJsonStr, WeatherWrapper.class);
        WeatherInfo weatherInfo = weatherBean.getWeatherinfo();
//        System.out.println(weatherBean);
        StringBuilder sb = new StringBuilder();
        
        sb.append(weatherInfo.getCity()+"今日天气: \n\n");
        sb.append("气温： "+weatherInfo.getTemp1()+"\n");
        sb.append("天气： "+weatherInfo.getWeather1()+"\n");
        sb.append("风速： "+weatherInfo.getWind1()+"\n");
        sb.append("穿衣指数： "+weatherInfo.getIndex_d()+"\n");
        sb.append("日期： "+weatherInfo.getDate_y()+", "+weatherInfo.getWeek()+"\n");
        return sb.toString();
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println(getDaliWeather());;
    }
    
}