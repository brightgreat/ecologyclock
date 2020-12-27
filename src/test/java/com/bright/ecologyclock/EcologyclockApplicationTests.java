package com.bright.ecologyclock;

import com.bright.ecologyclock.bean.UserBean;
import com.bright.ecologyclock.service.AESUtil;
import com.bright.ecologyclock.service.NingMengServer;
import com.bright.ecologyclock.service.PunchClock;
import com.bright.ecologyclock.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EcologyclockApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    PunchClock pusSvc;
    @Autowired
    NingMengServer nmSvc;
    @Autowired
    AESUtil aeSvc;

    @Test
    public void contextLoads() throws Exception {
//        UserBean userBean = userService.loginIn("a","a");
//        System.out.println("该用户ID为：");
//        System.out.println(userBean.getId());


//        boolean a = pusSvc.isWeekend();
//        System.out.println(a);

//        System.out.println(aeSvc.aesEncode("ASSA"));
//        System.out.println(aeSvc.aesDecode("qsDg6gaGa+li1KmJUk5Ctg=="));

//        System.out.println(nmSvc.callHttpNingMeng("https://lemonhd.org/attendance.php", "__cfduid=d3344cbfb5147d4bcd9afdce7d855b3f31608721487; c_secure_uid=MzQxMjQ%3D; c_secure_pass=c1fec63c3f96279e98469fef5bd33dd6; c_secure_ssl=eWVhaA%3D%3D; c_secure_tracker_ssl=eWVhaA%3D%3D; c_secure_login=bm9wZQ%3D%3D"));

/**
 * 飞机信息
 * url : https://linkhub.cool/user/checkin
 * cook: __cfduid=dc666a176f0bb36bab187bd2e5ccfab251608382636; _ga=GA1.2.756003259.1608382726; PHPSESSID=ho0ig5jt9bkj1sf0p9omeivr7a; uid=79135; email=3310579331%40qq.com; key=b961e59a2414c7dee56d458c9e23fe90dd96514882f1a; ip=2c71460783aaeba9412be12fe8c13f87; expire_in=1609175249; _gid=GA1.2.1676704882.1609088849; _gat=1; _gat_gtag_UA_162039658_1=1
 * 下次尝试
 * **/
        System.out.println(nmSvc.callHttpNingMeng("https://linkhub.cool/user/checkin", "__cfduid=dc666a176f0bb36bab187bd2e5ccfab251608382636; _ga=GA1.2.756003259.1608382726; PHPSESSID=ho0ig5jt9bkj1sf0p9omeivr7a; uid=79135; email=3310579331%40qq.com; key=b961e59a2414c7dee56d458c9e23fe90dd96514882f1a; ip=2c71460783aaeba9412be12fe8c13f87; expire_in=1609175249; _gid=GA1.2.1676704882.1609088849; _gat=1; _gat_gtag_UA_162039658_1=1"));


    }

}
