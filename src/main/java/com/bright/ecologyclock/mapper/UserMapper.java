package com.bright.ecologyclock.mapper;

import com.bright.ecologyclock.bean.UserBean;

import java.util.List;

public interface UserMapper {

    //登录时信息核对
    UserBean getInfo(String name,String password);

    //根据ID查询用户信息
    UserBean selectUserById(String id);

    //插入新的用户
    int insertUser(UserBean userBean);

    //删除用户
    int deleteUser(String id);

    //修改用户
    int updateUser(UserBean userBean);

    //查询所有用户
    List<UserBean> getAllUser();

    //根据接口的邮箱查询相关的所有信息
    UserBean getUser(String name);

}
