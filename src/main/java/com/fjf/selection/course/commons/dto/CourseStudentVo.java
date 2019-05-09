package com.fjf.selection.course.commons.dto;

import com.fjf.selection.course.domain.College;
import lombok.Data;

/**
 * @Classname CourseStudentVo
 * @Date 2019/5/8 22:05
 * @Created by Fjf
 */
@Data
public class CourseStudentVo {
    private Integer studentId;
    private String studentName;
    private String collegeName;
}
