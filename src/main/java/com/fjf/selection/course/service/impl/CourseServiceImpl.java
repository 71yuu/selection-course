package com.fjf.selection.course.service.impl;

import com.fjf.selection.course.commons.dto.CourseVo;
import com.fjf.selection.course.commons.dto.PageInfo;
import com.fjf.selection.course.domain.College;
import com.fjf.selection.course.domain.Course;
import com.fjf.selection.course.domain.Teacher;
import com.fjf.selection.course.mapper.CollegeMapper;
import com.fjf.selection.course.mapper.CourseMapper;
import com.fjf.selection.course.mapper.SelectedCourseMapper;
import com.fjf.selection.course.mapper.TeacherMapper;
import com.fjf.selection.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Classname CourseServiceImpl
 * @Date 2019/5/6 22:13
 * @Created by Fjf
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private SelectedCourseMapper selectedCourseMapper;

    @Override
    public PageInfo<CourseVo> page(Map<String, Object> params) {
        PageInfo<CourseVo> pageInfo = new PageInfo<>();

        int count = courseMapper.getCourseNum();
        List<Course> courses = courseMapper.page(params);

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
    public void save(Course course) {
        // 编辑
        if (course.getCourseId() != null) {
            courseMapper.updateByPrimaryKey(course);
        }

        // 新增
        else{
            courseMapper.insert(course);
        }
    }

    @Override
    public Course getById(Integer id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Integer courseId) {
        // 删除该课程选课信息
        selectedCourseMapper.deleteByCourseId(courseId);
        // 删除课程
        courseMapper.deleteByPrimaryKey(courseId);
    }

}
