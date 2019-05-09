package com.fjf.selection.course.service;

import com.fjf.selection.course.commons.dto.PageInfo;
import com.fjf.selection.course.commons.dto.TeacherVo;
import com.fjf.selection.course.domain.Course;
import com.fjf.selection.course.domain.Teacher;
import com.fjf.selection.course.domain.UserLogin;

import java.util.List;
import java.util.Map;

/**
 * @Classname TeacherService
 * @Date 2019/5/7 13:36
 * @Created by Fjf
 */
public interface TeacherService {
    /**
     * 查询所有教师
     * @return
     */
    List<Teacher> teacherList();

    /**
     * 根据 ID 获取教师
     * @param teacherId
     * @return
     */
    Teacher getById(Integer teacherId);

    /**
     * 分页查询
     * @param params
     * @return
     */
    PageInfo<TeacherVo> page(Map<String, Object> params);

    /**
     * 保存教师信息
     * @param teacher
     */
    void save(Teacher teacher);

    /**
     * 根据 ID 删除教师
     * @param userId
     */
    boolean deleteById(Integer userId);

    /**
     * 教师课程分页
     * @param params
     * @return
     */
    PageInfo<Course> coursePage(Map<String, Object> params);

    /**
     * 修改密码
     * @param userLogin
     */
    void updatePassword(UserLogin userLogin);
}
