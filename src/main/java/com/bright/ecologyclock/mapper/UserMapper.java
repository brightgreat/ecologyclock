package com.bright.ecologyclock.mapper;

import com.bright.ecologyclock.bean.UserBean;

public interface UserMapper {

    UserBean getInfo(String code,String psswd);
}
