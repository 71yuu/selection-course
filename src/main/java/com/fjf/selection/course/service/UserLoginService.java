package com.fjf.selection.course.service;

import com.fjf.selection.course.domain.UserLogin;

/**
 * @Classname UserLoginService
 * @Date 2019/5/6 20:14
 * @Created by Fjf
 */
public interface UserLoginService {
    /**
     * 登录业务
     * @param userName
     * @param password
     * @return
     */
    UserLogin login(String userName, String password);
}
