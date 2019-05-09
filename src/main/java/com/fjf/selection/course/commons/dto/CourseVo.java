package com.fjf.selection.course.commons.dto;

import com.fjf.selection.course.domain.College;
import com.fjf.selection.course.domain.Teacher;
import lombok.Data;

/**
 * Course 数据传输对象
 * @Classname CourseVo
 * @Date 2019/5/7 9:09
 * @Created by Fjf
 */
@Data
public class CourseVo {
    private Integer courseId;

    private String courseName;

    private Teacher teacher;

    private String courseTime;

    private String classRoom;

    private Integer courseWeek;

    private String courseType;

    private College college;

    private Integer score;
}
