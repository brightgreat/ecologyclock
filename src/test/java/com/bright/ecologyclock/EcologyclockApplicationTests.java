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

        System.out.println(aeSvc.aesEncode("ASSA"));
        System.out.println(aeSvc.aesDecode("qsDg6gaGa+li1KmJUk5Ctg=="));

//        System.out.println(nmSvc.callHttpNingMeng("https://lemonhd.org/attendance.php", "__cfduid=d3344cbfb5147d4bcd9afdce7d855b3f31608721487; c_secure_uid=MzQxMjQ%3D; c_secure_pass=c1fec63c3f96279e98469fef5bd33dd6; c_secure_ssl=eWVhaA%3D%3D; c_secure_tracker_ssl=eWVhaA%3D%3D; c_secure_login=bm9wZQ%3D%3D"));

    }

}
