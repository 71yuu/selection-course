package com.fjf.selection.course.domain;

import lombok.Data;

@Data
public class UserLogin {
    private String userId;

    private String userName;

    private String password;

    private Integer role;
}