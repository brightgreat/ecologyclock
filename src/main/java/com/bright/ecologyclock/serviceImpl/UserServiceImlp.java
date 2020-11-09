package com.bright.ecologyclock.serviceImpl;

import com.bright.ecologyclock.bean.UserBean;
import com.bright.ecologyclock.mapper.UserMapper;
import com.bright.ecologyclock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImlp implements UserService {

    //将DAO注入Service层
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserBean loginIn(String code, String psswd) {
        return userMapper.getInfo(code, psswd);
    }
}
