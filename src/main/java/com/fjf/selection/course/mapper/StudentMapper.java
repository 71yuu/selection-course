package com.fjf.selection.course.mapper;

import com.fjf.selection.course.domain.Student;
import java.util.List;
import java.util.Map;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(Student record);

    Student selectByPrimaryKey(Integer userid);

    List<Student> selectAll();

    int updateByPrimaryKey(Student record);

    /**
     * 查看是否有该院系的学生
     * @param collegeId
     * @return
     */
    int findCollegeById(Integer collegeId);

    /**
     * 获取学生总数
     * @return
     */
    int getStudentNum();

    /**
     * 分页查询
     * @param params
     * @return
     */
    List<Student> page(Map<String, Object> params);

    /**
     * 根据 ID 删除学生
     * @param studentId
     */
    void deleteById(Integer userId);

    /**
     * 根据 ID 集合获取学生集合
     * @param studentIdList
     * @return
     */
    List<Student> getStudentListById(List<Integer> studentIdList);
}