package com.fjf.selection.course.service.impl;

import com.fjf.selection.course.commons.dto.PageInfo;
import com.fjf.selection.course.commons.dto.TeacherVo;
import com.fjf.selection.course.domain.College;
import com.fjf.selection.course.domain.Course;
import com.fjf.selection.course.domain.Teacher;
import com.fjf.selection.course.domain.UserLogin;
import com.fjf.selection.course.mapper.CollegeMapper;
import com.fjf.selection.course.mapper.CourseMapper;
import com.fjf.selection.course.mapper.TeacherMapper;
import com.fjf.selection.course.mapper.UserloginMapper;
import com.fjf.selection.course.service.CourseService;
import com.fjf.selection.course.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Classname TeacherServiceImpl
 * @Date 2019/5/7 13:37
 * @Created by Fjf
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private UserloginMapper userloginMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseService courseService;

    @Override
    public List<Teacher> teacherList() {
        return teacherMapper.selectAll();
    }

    @Override
    public Teacher getById(Integer teacherId) {
        return teacherMapper.selectByPrimaryKey(teacherId);
    }

    @Override
    public PageInfo<TeacherVo> page(Map<String, Object> params) {
        PageInfo<TeacherVo> pageInfo = new PageInfo<>();

        int count = teacherMapper.getTeacherNum();
        List<Teacher> teacherList = teacherMapper.page(params);

        List<TeacherVo> teacherVoList = new ArrayList<>();
        for (Teacher teacher : teacherList) {
            College college = collegeMapper.selectByPrimaryKey(teacher.getCollegeId());
            TeacherVo teacherVo = new TeacherVo();
            teacherVo.setUserId(teacher.getUserId());
            teacherVo.setUserName(teacher.getUserName());
            teacherVo.setSex(teacher.getSex());
            teacherVo.setBirthYear(teacher.getBirthYear());
            teacherVo.setDegree(teacher.getDegree());
            teacherVo.setTitle(teacher.getTitle());
            teacherVo.setGrade(teacher.getGrade());
            teacherVo.setCollege(college);
            teacherVoList.add(teacherVo);
        }

        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(teacherVoList);
        return pageInfo;
    }

    @Override
    public void save(Teacher teacher) {
        // 编辑
        if (teacher.getUserId() != null) {
            teacherMapper.updateByPrimaryKey(teacher);
        }

        // 新增
        else{
            teacherMapper.insert(teacher);
            UserLogin userLogin = new UserLogin();
            userLogin.setUserName(String.valueOf(teacher.getUserId()));
            userLogin.setPassword("123456");
            userLogin.setRole(1);
            userloginMapper.insert(userLogin);
        }
    }

    @Override
    public boolean deleteById(Integer userId) {
        // 查找教师是否有授课信息，有则不让删除
        int count = courseMapper.findTeacherById(userId);

        // 有
        if (count > 0) {
            return false;
        }

        // 没有
        else {
            // 删除老师登录信息
            userloginMapper.deleteByUserName(String.valueOf(userId));

            // 删除教师信息
            teacherMapper.deleteByPrimaryKey(userId);

            return true;
        }
    }

    @Override
    public PageInfo<Course> coursePage(Map<String, Object> params) {
        PageInfo<Course> pageInfo = new PageInfo<>();

        int teacherId = (int) params.get("teacherId");

        int count = courseMapper.getByTeacherId(teacherId);
        List<Course> courseList = courseMapper.coursePageByTeacherId(params);

        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(courseList);
        return pageInfo;
    }

    @Override
    public void updatePassword(UserLogin userLogin) {
        userloginMapper.updateByPrimaryKey(userLogin);
    }
}
