package com.fjf.selection.course.commons.dto;

import com.fjf.selection.course.domain.College;
import lombok.Data;

import java.util.Date;

/**
 * Teacher 数据传输对象
 * @Classname TeacherVo
 * @Date 2019/5/7 22:30
 * @Created by Fjf
 */
@Data
public class TeacherVo {
    private Integer userId;

    private String userName;

    private String sex;

    private Date birthYear;

    private String degree;

    private String title;

    private Date grade;

    private College college;
}
