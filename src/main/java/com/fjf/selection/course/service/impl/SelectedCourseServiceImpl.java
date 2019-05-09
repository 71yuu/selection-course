package com.fjf.selection.course.service.impl;

import com.fjf.selection.course.commons.dto.CourseStudentVo;
import com.fjf.selection.course.commons.dto.PageInfo;
import com.fjf.selection.course.commons.dto.SelectedCourseVo;
import com.fjf.selection.course.domain.*;
import com.fjf.selection.course.mapper.*;
import com.fjf.selection.course.service.SelectedCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Clssname SelectedCourseServiceImpl
 * @Date 2019/5/8 11:07
 * @Created by Fjf
 */
@Service
public class SelectedCourseServiceImpl implements SelectedCourseService {

    @Autowired
    private SelectedCourseMapper selectedCourseMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageInfo<SelectedCourseVo> page(Map<String, Object> params) {
        PageInfo<SelectedCourseVo> pageInfo = new PageInfo<>();

        int count = selectedCourseMapper.getSelectedCourseNum();
        List<SelectedCourse> selectedCourseList = selectedCourseMapper.page(params);

        List<SelectedCourseVo> selectedCourseVoList = new ArrayList<>();
        for (SelectedCourse selectedCourse : selectedCourseList) {
            SelectedCourseVo selectedCourseVo = new SelectedCourseVo();
            selectedCourseVo.setCourseId(selectedCourse.getCourseId());

            // 根据课程 ID 查询课程
            Course course = courseMapper.selectByPrimaryKey(selectedCourse.getCourseId());
            selectedCourseVo.setCourseName(course.getCourseName());
            selectedCourseVo.setCourseType(course.getCourseType());
            selectedCourseVo.setOpenTime(course.getCourseTime());

            // 根据课程 ID 查询教师
            Teacher teacher = teacherMapper.selectByPrimaryKey(course.getTeacherId());
            selectedCourseVo.setTeacherName(teacher.getUserName());

            // 根据课程 ID 查询院系
            College college = collegeMapper.selectByPrimaryKey(course.getCollegeId());
            selectedCourseVo.setCollegeName(college.getCollegeName());

            // 根据课程 ID 查询已选人数
            int stuCount = selectedCourseMapper.getStuCount(selectedCourse.getCourseId());
            selectedCourseVo.setStuCount(stuCount);
            selectedCourseVoList.add(selectedCourseVo);
        }

        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(selectedCourseVoList);
        return pageInfo;
    }

    @Override
    public PageInfo<CourseStudentVo> studentPage(Map<String, Object> params) {
        PageInfo<CourseStudentVo> pageInfo = new PageInfo<>();

        int courseId = (Integer) params.get("courseId");

        int count = selectedCourseMapper.getStuCount(courseId);

        List<Integer> studentIdList = selectedCourseMapper.getStudentList(params);
        List<Student> studentList = studentMapper.getStudentListById(studentIdList);

        List<CourseStudentVo> courseStudentVoList = new ArrayList<>();
        for (Student student : studentList) {
            CourseStudentVo courseStudentVo = new CourseStudentVo();
            courseStudentVo.setStudentId(student.getUserId());
            courseStudentVo.setStudentName(student.getUserName());

            // 根据院系 ID 查询院系
            College college = collegeMapper.selectByPrimaryKey(student.getCollegeId());
            courseStudentVo.setCollegeName(college.getCollegeName());

            courseStudentVoList.add(courseStudentVo);
        }

        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(courseStudentVoList);
        return pageInfo;
    }
}
