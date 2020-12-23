package com.bright.ecologyclock.service;


import com.alibaba.fastjson.JSON;
import com.bright.ecologyclock.bean.UserBean;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class PunchClock {
    private ExecutorService executor = Executors.newCachedThreadPool() ;

    public void  punClock(UserBean ub) throws Exception {
        if (isWeekend()) {
            SendEmailForEmail("周末不需要打卡", "周末不需要打卡", ub.getName());
            return;
        }
        executor.submit(new Runnable(){
            @Override
            public void run() {
                try {
                    Integer random = getRandomForHundred();
                    System.out.println(random);
                    //进程随机睡眠一个时间
                    Thread.sleep(random * 1000);
                    String location = getAddressForgaode(ub);
                    String clockRes = call(ub, location);
//                    return clockRes;
                }catch (Exception e){
                    throw new RuntimeException("报错啦！！");
                }
            }
        });

    }

    /**
     * DK程序call
     * @param ub
     * @param location
     * @return
     * @throws Exception
     */
    public static String call(UserBean ub, String location) throws Exception {
        String resul;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] loc = location.split(",");
        //通过登录链接得到一个cookie
        String loginUrl = "http://oa.ittx.com.cn/api/hrm/login/checkLogin"; //?loginid=hlwang@ittx.com.cn&userpassword=ec53c91144781b29d864d6a16a13e506_random_
        String dakaUrl = "http://oa.ittx.com.cn/api/hrm/kq/attendanceButton/punchButton?address=" + ub.getEcologyAddress() + "&longitude=" + loc[0] + "&latitude=" + loc[1] + "&locationshowaddress=1&locationid=25";

        //将加密的密码进行解密传输
        String pwd = decord(ub.getEcologyPasswd());
        //登录获取session
        String sessionId = callHttp(loginUrl + "?loginid=" + ub.getEcologyCode() + "&userpassword=" + pwd, "", true);
        //打卡程序请求
        String result = callHttp(dakaUrl, sessionId, false);

        if (result.toLowerCase().contains("成功")) {
            System.out.println("===========" + ub.getEcologyCode() + "=打卡成功======“”" + df.format(new Date()) + "==============");
            //成功邮件提醒
            SendEmailForEmail("打卡成功提醒", ub.getEcologyCode() + "打卡成功，打卡时间为：" + df.format(new Date()) + ";" + result, ub.getName());
            resul = "success";
        } else {
            System.out.println("===========" + ub.getEcologyCode() + "=打卡失败======“”" + df.format(new Date()) + "==============");
            SendEmailForEmail("打卡失败提醒", ub.getEcologyCode() + "打卡失败，打卡时间为：" + df.format(new Date()) + ";失败原因为：" + result, ub.getName());
            resul = "failed";
        }
        return resul;
    }

    /**
     * 通过高德接口获取经纬度信息
     *
     * @param ub
     * @return
     * @throws Exception
     */
    public static String getAddressForgaode(UserBean ub) throws Exception {
        //调用高德获取经纬度
        String location;
        String url = "https://restapi.amap.com/v3/place/text?s=rsv3&key=8325164e247e15eea68b59e89200988b&page=1&offset=1&city=310000&language=zh_cn&callback=jsonp_719899_&platform=JS&logversion=2.0&sdkversion=1.3&appname=https://lbs.amap.com/console/show/picker&csid=B95605C8-7F47-4B5A-A5A9-1AEC355E3A39&keywords=";
        String gaodeResult = gaoDeHttpPost(url + ub.getEcologyAddress());
        String gaodeJson = subString(gaodeResult, "(", ")");
        Map mapTypes = JSON.parseObject(gaodeJson);
        System.out.println("这个是用JSON类的parseObject来解析JSON字符串!!!");

        String s = mapTypes.get("pois").toString();
        String d = s.substring(1, s.substring(1).length());
        Map map = JSON.parseObject(d);

        location = (String) map.get("location");
        System.out.println(location);
        return location;
    }

    /**
     * 截取字符串str中指定字符 strStart、strEnd之间的字符串
     *
     * @param str
     * @param strStart
     * @param strEnd
     * @return
     */
    public static String subString(String str, String strStart, String strEnd) {

        /* 找出指定的2个字符在 该字符串里面的 位置 */
        int strStartIndex = str.indexOf(strStart);
        int strEndIndex = str.indexOf(strEnd);

        /* index 为负数 即表示该字符串中 没有该字符 */
        if (strStartIndex < 0) {
            return "字符串 :---->" + str + "<---- 中不存在 " + strStart + ", 无法截取目标字符串";
        }
        if (strEndIndex < 0) {
            return "字符串 :---->" + str + "<---- 中不存在 " + strEnd + ", 无法截取目标字符串";
        }
        /* 开始截取 */
        String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
        return result;
    }


    /**
     * 发送邮件
     *
     * @throws Exception
     */
    public static void SendEmailForEmail(String mailTittle, String mailText, String email) throws Exception {
        String mailFrom = "1877143930@qq.com";
        String password_mailFrom = "ivwclrzswndkdeeg";
        String mailTo = email;
//        String mailTittle = "打卡失败提醒";
//        String mailText = firest ? "以下用户打卡失败，请处理" + user + "这是第一次打卡，5分钟后会进行补打" : "以下用户补打卡失败，请处理：" + user + "这是补打卡";
        String mail_host = "smtp.qq.com";

        Properties prop = new Properties();
        prop.setProperty("mail.host", mail_host);
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        // 使用JavaMail发送邮件的5个步骤

        // 1、创建session
        Session session = Session.getInstance(prop);
        // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(false);
        // 2、通过session得到transport对象
        Transport ts = session.getTransport();
        // 3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
        ts.connect(mail_host, mailFrom, password_mailFrom);
        // 4、创建邮件
        Message message = createSimpleMailForOnlyEmail(session, mailFrom, mailTo, mailTittle, mailText);
        // 5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();

    }

    /**
     * @Method: createSimpleMail
     * @Description: 创建一封只包含文本的邮件
     */
    public static MimeMessage createSimpleMailForOnlyEmail(Session session, String mailfrom, String mailTo, String mailTittle,
                                                           String mailText) throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress(mailfrom));
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发

        //设定单人收件
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
        //设定多人收件
//        message.setRecipients(Message.RecipientType.TO, to_address);
        // 邮件的标题
        message.setSubject(mailTittle);
        // 邮件的文本内容
        message.setContent(mailText, "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;
    }

    /**
     * @Method: createSimpleMail
     * @Description: 创建一封只包含文本的邮件
     */
    public static MimeMessage createSimpleMail(Session session, String mailfrom, String mailTo, String mailTittle,
                                               String mailText, InternetAddress to_address[]) throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress(mailfrom));
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发

        //设定单人收件
//        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
        //设定多人收件
        message.setRecipients(Message.RecipientType.TO, to_address);
        // 邮件的标题
        message.setSubject(mailTittle);
        // 邮件的文本内容
        message.setContent(mailText, "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;
    }

    /**
     * 这只是一个高德的post信息接口
     *
     * @param callURL
     * @return
     * @throws Exception
     */
    public static String gaoDeHttpPost(String callURL) throws Exception {
        String result = "";
//        String sessionId = "";
        URL u0 = new URL(callURL);
        HttpURLConnection conn = (HttpURLConnection) u0.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "text/plain");
        conn.setRequestProperty("Content-Language", "en-US");
        conn.setConnectTimeout(30000);
        conn.setReadTimeout(30000);
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        // 自动捕获网页编码，并按其编码方式读取网页内容
        String charset = getChareset(conn.getContentType());
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        result = buffer.toString();

        conn.disconnect();
        System.out.println(result);
        return result;

    }

    /**
     * 登录和打卡的Post请求
     *
     * @param callURL
     * @param sessionId
     * @param isLogin
     * @return
     * @throws Exception
     */
    public static String callHttp(String callURL, String sessionId, boolean isLogin) throws Exception {
        String result = "";
        URL u0 = new URL(callURL);
        HttpURLConnection conn = (HttpURLConnection) u0.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "text/plain");
        conn.setRequestProperty("Content-Language", "en-US");
        if (!isLogin) {
            conn.setRequestProperty("Cookie", sessionId);
        }
        conn.setConnectTimeout(30000);
        conn.setReadTimeout(30000);
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        String key = null;
        for (int i = 1; (key = conn.getHeaderFieldKey(i)) != null; i++) {
            if (key.equalsIgnoreCase("set-cookie")) {
                sessionId = conn.getHeaderField(key);
                sessionId = sessionId.substring(0, sessionId.indexOf(";"));
                break;
            }
        }
        // 自动捕获网页编码，并按其编码方式读取网页内容
        String charset = getChareset(conn.getContentType());
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        result = buffer.toString();

        conn.disconnect();
        System.out.println(result);
//        return result;
        //如果登录成功，这里就把session返回出去
        if (isLogin && result.toLowerCase().contains("成功")) {
            return sessionId;
        } else {
            return result;
        }

    }

    /**
     * 获取网页编码方式
     *
     * @param contentType
     * @return
     */
    public static String getChareset(String contentType) {
        int i = contentType == null ? -1 : contentType.indexOf("charset=");
        return i == -1 ? "UTF-8" : contentType.substring(i + 8);
    }

    /**
     * Base64解密，避免密文明文传输
     *
     * @param data
     * @return
     */
    public static String decord(String data) {
        try {
            // BASE64加密
//            BASE64Encoder encoder = new BASE64Encoder();
//            String data = encoder.encode(DATA.getBytes());
//            System.out.println("BASE64加密：" + data);

            // BASE64解密
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(data);
//            System.out.println("BASE64解密：" + new String(bytes));
            return new String(bytes);
            // 结果
            // BASE64加密：Y29tLmJhc2U2NC5kZW1v
            // BASE64解密：com.base64.demo
        } catch (Exception e) {
            System.out.println("BASE64加解密异常");
            e.printStackTrace();
            return e.toString();
        }

    }

    /**
     * 获取100内的随机数
     * @return
     */
    public static  Integer getRandomForHundred(){
        return (int)(100 * Math.random() + 1);
    }


    /**
     * 判断是否是周末
     * @return
     */
    public boolean  isWeekend(){
        try {
            Calendar cal = Calendar.getInstance();
            String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
            Date date;
            try {
                date = new Date();
                cal.setTime(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //一周的第几天
            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0)
                w = 0;
            System.out.println("今天是" + weekDays[w]);
            if(weekDays[w].equals("星期日") || weekDays[w].equals("星期六")){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            System.out.println(e.toString());
            return false;
        }

    }

}
