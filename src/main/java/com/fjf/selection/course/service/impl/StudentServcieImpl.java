package com.fjf.selection.course.service.impl;

import com.fjf.selection.course.commons.dto.CourseVo;
import com.fjf.selection.course.commons.dto.PageInfo;
import com.fjf.selection.course.commons.dto.StudentVo;
import com.fjf.selection.course.domain.*;
import com.fjf.selection.course.mapper.*;
import com.fjf.selection.course.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Classname StudentServcieImpl
 * @Date 2019/5/8 9:55
 * @Created by Fjf
 */
@Service
public class StudentServcieImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private UserloginMapper userloginMapper;

    @Autowired
    private SelectedCourseMapper selectedCourseMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Student getById(Integer studentId) {
        return studentMapper.selectByPrimaryKey(studentId);
    }

    @Override
    public PageInfo<StudentVo> page(Map<String, Object> params) {
        PageInfo<StudentVo> pageInfo = new PageInfo<>();

        int count = studentMapper.getStudentNum();
        List<Student> studentList = studentMapper.page(params);

        List<StudentVo> studentVoList = new ArrayList<>();
        for (Student student : studentList) {
            College college = collegeMapper.selectByPrimaryKey(student.getCollegeId());
            StudentVo studentVo = new StudentVo();
            studentVo.setUserId(student.getUserId());
            studentVo.setUserName(student.getUserName());
            studentVo.setSex(student.getSex());
            studentVo.setBirthYear(student.getBirthYear());
            studentVo.setGrade(student.getGrade());
            studentVo.setCollege(college);
            studentVoList.add(studentVo);
        }

        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(studentVoList);
        return pageInfo;
    }

    @Override
    public void save(Student student) {
        // 编辑
        if (student.getUserId() != null) {
            studentMapper.updateByPrimaryKey(student);
        }

        // 新增
        else{
            studentMapper.insert(student);
            UserLogin userLogin = new UserLogin();
            userLogin.setUserName(String.valueOf(student.getUserId()));
            userLogin.setPassword("123456");
            userLogin.setRole(2);
            userloginMapper.insert(userLogin);
        }
    }

    @Override
    public void deleteById(Integer userId) {
        // 删除选课信息
        selectedCourseMapper.deleteByStudentId(userId);
        // 删除登录信息
        userloginMapper.deleteByUserName(String.valueOf(userId));
        // 删除学生信息
        studentMapper.deleteById(userId);
    }

    @Override
    public PageInfo<CourseVo> optionalPage(Map<String, Object> params) {
        PageInfo<CourseVo> pageInfo = new PageInfo<>();

        int studentId = (int) params.get("studentId");

        // 查询学生已选课程ID集合
        List<Integer> selectedCourseIdList = selectedCourseMapper.getCourseIdByStudentId(studentId);
        params.put("selectedCourseIdList", selectedCourseIdList);

        // 查询未选课程总数
        int count = courseMapper.getOptionalNum(selectedCourseIdList);

        // 根据已选课程 ID 查询出未选课程
        List<Course> courses = courseMapper.getCourseListByCourseId(params);

        List<CourseVo> courseVoList = new ArrayList<>();
        for (Course course : courses) {
            Teacher teacher = teacherMapper.selectByPrimaryKey(course.getTeacherId());
            College college = collegeMapper.selectByPrimaryKey(course.getCollegeId());
            CourseVo courseVo = new CourseVo();
            courseVo.setCourseId(course.getCourseId());
            courseVo.setCourseName(course.getCourseName());
            courseVo.setTeacher(teacher);
            courseVo.setCourseTime(course.getCourseTime());
            courseVo.setClassRoom(course.getClassRoom());
            courseVo.setCourseWeek(course.getCourseWeek());
            courseVo.setCourseType(course.getCourseType());
            courseVo.setCollege(college);
            courseVo.setScore(course.getScore());
            courseVoList.add(courseVo);
        }

        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(courseVoList);
        return pageInfo;
    }

    @Override
    public void selectCourse(int studentId, int courseId) {
         SelectedCourse selectedCourse = new SelectedCourse();
         selectedCourse.setStudentId(studentId);
         selectedCourse.setCourseId(courseId);
         selectedCourseMapper.insert(selectedCourse);
    }

    @Override
    public PageInfo<CourseVo> retreatPage(Map<String, Object> params) {
        PageInfo<CourseVo> pageInfo = new PageInfo<>();

        int studentId = (int) params.get("studentId");

        // 查询学生已选课程ID集合
        List<Integer> selectedCourseIdList = selectedCourseMapper.getCourseIdByStudentId(studentId);
        int count = selectedCourseIdList.size();

        params.put("selectedCourseIdList", selectedCourseIdList);

        // 根据已选课程 ID 查询出已选课程
        List<Course> courses = courseMapper.getRetreatCourseListByCourseId(params);

        List<CourseVo> courseVoList = new ArrayList<>();
        for (Course course : courses) {
            Teacher teacher = teacherMapper.selectByPrimaryKey(course.getTeacherId());
            College college = collegeMapper.selectByPrimaryKey(course.getCollegeId());
            CourseVo courseVo = new CourseVo();
            courseVo.setCourseId(course.getCourseId());
            courseVo.setCourseName(course.getCourseName());
            courseVo.setTeacher(teacher);
            courseVo.setCourseTime(course.getCourseTime());
            courseVo.setClassRoom(course.getClassRoom());
            courseVo.setCourseWeek(course.getCourseWeek());
            courseVo.setCourseType(course.getCourseType());
            courseVo.setCollege(college);
            courseVo.setScore(course.getScore());
            courseVoList.add(courseVo);
        }

        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(courseVoList);
        return pageInfo;
    }

    @Override
    public void retreatCourse(int studentId, Integer courseId) {
        SelectedCourse selectedCourse = new SelectedCourse();
        selectedCourse.setStudentId(studentId);
        selectedCourse.setCourseId(courseId);
        selectedCourseMapper.deleteByCourseIdAndStudentId(selectedCourse);
    }

    @Override
    public void updatePassword(UserLogin userLogin) {
        userloginMapper.updateByPrimaryKey(userLogin);
    }
}
