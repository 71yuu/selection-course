package com.fjf.selection.course.service.impl;

import com.fjf.selection.course.domain.UserLogin;
import com.fjf.selection.course.mapper.UserloginMapper;
import com.fjf.selection.course.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname UserloginServiceImpl
 * @Date 2019/5/6 20:15
 * @Created by Fjf
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserloginMapper userloginMapper;

    @Override
    public UserLogin login(String userName, String password) {
        UserLogin userLogin = userloginMapper.findByUserName(userName);
        // 登录失败
        if (!userLogin.getPassword().equals(password)) {
            return null;
        }
        return userLogin;
    }
}
