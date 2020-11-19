package com.bright.ecologyclock.controller;

import com.bright.ecologyclock.bean.UserBean;
import com.bright.ecologyclock.service.UserService;
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
    UserService userService;

    @RequestMapping("/login")
    public String show() {
        return "login";
    }

    @RequestMapping(value = "/loginIn", method = RequestMethod.POST)
    public String login(String name, String password) {
        UserBean userBean = userService.loginIn(name, password);
        if (userBean != null) {
            return "success";
        } else {
            return "error";
        }
    }

    /**
     * 打卡接口
     * @param request
     * @param params
     * @return
     */
    @RequestMapping(value = "/ecologyclock")
    public String sumDaily(HttpServletRequest request, @RequestParam Map<String, String> params) {
        String email = params.get("email");
        System.out.println(email);
        return "success";
    }
}
