package com.fjf.selection.course.domain;

import lombok.Data;

@Data
public class Course {
    private Integer courseId;

    private String courseName;

    private Integer teacherId;

    private String courseTime;

    private String classRoom;

    private Integer courseWeek;

    private String courseType;

    private Integer collegeId;

    private Integer score;
}