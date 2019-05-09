package com.fjf.selection.course.commons.dto;

import lombok.Data;


/**
 * @Classname SelectedCourseVo
 * @Date 2019/5/8 11:00
 * @Created by Fjf
 */
@Data
public class SelectedCourseVo {
    private Integer courseId;
    private String courseName;
    private String teacherName;
    private String openTime;
    private String courseType;
    private String collegeName;
    private int stuCount;
}
