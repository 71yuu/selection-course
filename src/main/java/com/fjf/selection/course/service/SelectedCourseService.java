package com.fjf.selection.course.service;

import com.fjf.selection.course.commons.dto.CourseStudentVo;
import com.fjf.selection.course.commons.dto.PageInfo;
import com.fjf.selection.course.commons.dto.SelectedCourseVo;

import java.util.Map;

/**
 * @Classname SelectedCourseService
 * @Date 2019/5/8 11:07
 * @Created by Fjf
 */
public interface SelectedCourseService {
    /**
     * 分页查询
     * @param params
     * @return
     */
    PageInfo<SelectedCourseVo> page(Map<String, Object> params);

    /**
     * 学生分页查询
     * @param params
     * @return
     */
    PageInfo<CourseStudentVo> studentPage(Map<String, Object> params);
}
