package com.fjf.selection.course.mapper;

import com.fjf.selection.course.domain.UserLogin;
import java.util.List;

public interface UserloginMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(UserLogin record);

    UserLogin selectByPrimaryKey(Integer userid);

    List<UserLogin> selectAll();

    int updateByPrimaryKey(UserLogin record);

    /**
     * 根据用户名查找登录信息
     * @param userName
     * @return
     */
    UserLogin findByUserName(String userName);

    /**
     * 删除登录信息
     * @param userName
     */
    void deleteByUserName(String uerName);
}