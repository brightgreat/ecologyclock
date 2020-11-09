package com.bright.ecologyclock.controller;

import com.bright.ecologyclock.bean.UserBean;
import com.bright.ecologyclock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String show() {
        return "login";
    }

    @RequestMapping(value = "/loginIn", method = RequestMethod.POST)
    public String login(String code, String psswdd) {
        UserBean userBean = userService.loginIn(code, psswdd);
        if (userBean != null) {
            return "success";
        } else {
            return "error";
        }
    }
}
