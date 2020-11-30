package com.bright.ecologyclock.controller;

import com.bright.ecologyclock.bean.UserBean;
import com.bright.ecologyclock.service.UserService;
import com.bright.ecologyclock.service.punchClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
public class LoginController {

    @Autowired
    UserService usSvc;

    @RequestMapping("/login")
    public String show() {
        return "login";
    }

    @RequestMapping(value = "/loginIn", method = RequestMethod.POST)
    public String login(String name, String password) {
        UserBean userBean = usSvc.loginIn(name, password);
        if (userBean != null) {
            return "success";
        } else {
            return "error";
        }
    }

    /**
     * 打卡接口
     *
     * @param request
     * @param params
     * @return
     */
    @RequestMapping(value = "/ecologyclock")
    public String sumDaily(HttpServletRequest request, @RequestParam Map<String, String> params) throws Exception {
        String name = params.get("email");
        System.out.println(name);
        UserBean userBean = usSvc.getUser(name);
        //这里采用异步调用直接返回success
        punchClock pun = new punchClock();
        pun.punClock(userBean);
        return "ecoloSuccess";
//        if ("success".equals(res)) {
//            return "ecoloSuccess";
//        } else {
//            return "error";
//        }

    }
}
