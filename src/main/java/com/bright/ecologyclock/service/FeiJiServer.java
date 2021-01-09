package com.bright.ecologyclock.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class FeiJiServer {

    /**
     * 柠檬的打卡主要方法
     * @param httpurl ：https://linkhub.cool/user/checkin
     * @param sessId ：__cfduid=dc666a176f0bb36bab187bd2e5ccfab251608382636; _ga=GA1.2.756003259.1608382726; uid=79135; email=3310579331%40qq.com; key=28b6d6f66386cdc4d6f8175e7570482618cc39754a639; ip=6f251d4f85526d10613f246468873bb9; expire_in=1610557137; _gid=GA1.2.1174922024.1609952340; _gat=1; _gat_gtag_UA_162039658_1=1
     * @return
     * @throws Exception
     */
    public static String callHttpNingMeng(String httpurl, String sessId) throws Exception {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("POST");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            connection.setRequestProperty("accept", "application/json, text/javascript, */*; q=0.01");
            // 以下的头信息，都可以存在cookie里进行传输
            connection.setRequestProperty("accept-encoding", "gzip, deflate, br");
            connection.setRequestProperty("accept-language", "zh-CN,zh;q=0.9");
            connection.setRequestProperty("cache-control", "no-cache");
            connection.setRequestProperty("origin", "https://linkhub.cool");
            connection.setRequestProperty("pragma", "no-cache");
            connection.setRequestProperty("referer", "https://linkhub.cool/user");
            connection.setRequestProperty("sec-fetch-dest", "empty");
            connection.setRequestProperty("sec-fetch-mode", "cors");
            connection.setRequestProperty("sec-fetch-site", "same-origin");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("cookie", sessId);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }

        return result;
    }
}
