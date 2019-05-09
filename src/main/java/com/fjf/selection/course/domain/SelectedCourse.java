package com.fjf.selection.course.domain;

import lombok.Data;

@Data
public class SelectedCourse {
    private Integer courseId;

    private Integer studentId;

    private Integer mark;
}