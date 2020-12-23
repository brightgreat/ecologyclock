package com.bright.ecologyclock;

import com.bright.ecologyclock.bean.UserBean;
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

    @Test
    public void contextLoads() {
//        UserBean userBean = userService.loginIn("a","a");
//        System.out.println("该用户ID为：");
//        System.out.println(userBean.getId());
        boolean a = pusSvc.isWeekend();
        System.out.println(a);
    }

}
