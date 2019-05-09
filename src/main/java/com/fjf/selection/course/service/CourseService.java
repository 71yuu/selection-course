package com.fjf.selection.course.service;

import com.fjf.selection.course.commons.dto.CourseVo;
import com.fjf.selection.course.commons.dto.PageInfo;
import com.fjf.selection.course.domain.Course;

import java.util.List;
import java.util.Map;

/**
 * @Classname CourseService
 * @Date 2019/5/6 22:12
 * @Created by Fjf
 */
public interface CourseService {
    /**
     * 分页查询
     * @param params
     * @return
     */
    PageInfo<CourseVo> page(Map<String, Object> params);

    /**
     * 保存课程
     * @param course
     */
    void save(Course course);

    /**
     * 根据 Id 获取课程
     * @param id
     * @return
     */
    Course getById(Integer id);

    /**
     * 根据 ID 删除课程
     * @param courseId
     */
    void deleteById(Integer courseId);

}
