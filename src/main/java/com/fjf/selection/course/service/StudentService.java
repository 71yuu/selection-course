package com.fjf.selection.course.service;

import com.fjf.selection.course.commons.dto.CourseVo;
import com.fjf.selection.course.commons.dto.PageInfo;
import com.fjf.selection.course.commons.dto.StudentVo;
import com.fjf.selection.course.domain.Student;
import com.fjf.selection.course.domain.UserLogin;

import java.util.Map;

/**
 * @Classname StudentService
 * @Date 2019/5/8 9:54
 * @Created by Fjf
 */
public interface StudentService {

    /**
     * 根据 ID 查询学生
     * @param studentId
     * @return
     */
    Student getById(Integer studentId);

    /**
     * 分页查询
     * @param params
     * @return
     */
    PageInfo<StudentVo> page(Map<String, Object> params);

    /**
     * 保存学生信息
     * @param student
     */
    void save(Student student);

    /**
     * 根据 ID 删除学生
     * @param studentId
     * @return
     */
    void deleteById(Integer userId);

    /**
     * 可选课程分页
     * @param params
     * @return
     */
    PageInfo<CourseVo> optionalPage(Map<String, Object> params);

    /**
     * 选课
     * @param studentId
     * @param courseId
     */
    void selectCourse(int studentId, int courseId);

    /**
     * 已选课程分页
     * @param params
     * @return
     */
    PageInfo<CourseVo> retreatPage(Map<String, Object> params);

    /**
     * 退课
     * @param studentId
     * @param courseId
     */
    void retreatCourse(int studentId, Integer courseId);

    /**
     * 修改密码
     * @param userLogin
     */
    void updatePassword(UserLogin userLogin);
}
