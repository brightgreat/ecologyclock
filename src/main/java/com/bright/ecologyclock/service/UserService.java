package com.bright.ecologyclock.service;

import com.bright.ecologyclock.bean.UserBean;

public interface UserService {

    UserBean loginIn(String code, String psswd);

}
