package com.fjf.selection.course.service;

import com.fjf.selection.course.commons.dto.PageInfo;
import com.fjf.selection.course.domain.College;

import java.util.List;
import java.util.Map;

/**
 * @Classname CollegeService
 * @Date 2019/5/7 13:37
 * @Created by Fjf
 */
public interface CollegeService {
    /**
     * 查询所有院系
     * @return
     */
    List<College> collegeList();

    /**
     * 根据 ID 获取院系
     * @param collegeId
     * @return
     */
    College getById(Integer collegeId);

    /**
     * 分页查询
     * @param params
     * @return
     */
    PageInfo<College> page(Map<String, Object> params);

    /**
     * 保存院系信息
     * @param college
     */
    void save(College college);

    /**
     * 根据 ID 删除院系信息
     * @param collegeId
     */
    boolean deleteById(Integer collegeId);
}
