package com.fjf.selection.course.mapper;

import com.fjf.selection.course.domain.College;
import java.util.List;
import java.util.Map;

public interface CollegeMapper {
    int deleteByPrimaryKey(Integer collegeid);

    int insert(College record);

    College selectByPrimaryKey(Integer collegeid);

    List<College> selectAll();

    int updateByPrimaryKey(College record);

    /**
     * 获取所有院系数量
     * @return
     */
    int getCollegeNum();

    /**
     * 分页查询
     * @param params
     * @return
     */
    List<College> page(Map<String, Object> params);
}