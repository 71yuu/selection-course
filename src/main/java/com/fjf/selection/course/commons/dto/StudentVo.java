package com.fjf.selection.course.commons.dto;

import com.fjf.selection.course.domain.College;
import lombok.Data;

import java.util.Date;

/**
 * @Classname StudentVo
 * @Date 2019/5/8 10:00
 * @Created by Fjf
 */
@Data
public class StudentVo {
    private Integer userId;

    private String userName;

    private String sex;

    private Date birthYear;

    private Date grade;

    private College college;
}
