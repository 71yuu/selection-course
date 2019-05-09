package com.fjf.selection.course.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Teacher {
    private Integer userId;

    private String userName;

    private String sex;

    private Date birthYear;

    private String degree;

    private String title;

    private Date grade;

    private Integer collegeId;
}