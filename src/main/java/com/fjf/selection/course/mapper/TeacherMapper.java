package com.fjf.selection.course.mapper;

import com.fjf.selection.course.domain.Teacher;
import java.util.List;
import java.util.Map;

public interface TeacherMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Teacher record);

    Teacher selectByPrimaryKey(Integer userid);

    List<Teacher> selectAll();

    int updateByPrimaryKey(Teacher record);

    /**
     * 查询所有教师数量
     * @return
     */
    int getTeacherNum();

    /**
     * 分页查询
     * @param params
     * @return
     */
    List<Teacher> page(Map<String, Object> params);

    /**
     * 查看该院系是否有课程
     * @param collegeId
     * @return
     */
    int findCollegeById(Integer collegeId);
}