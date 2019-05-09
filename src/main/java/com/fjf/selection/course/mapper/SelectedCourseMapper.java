package com.fjf.selection.course.mapper;

import com.fjf.selection.course.domain.SelectedCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SelectedCourseMapper {
    int insert(SelectedCourse record);

    List<SelectedCourse> selectAll();

    /**
     * 删除该课程的选课信息
     * @param courseId
     */
    void deleteByCourseId(Integer courseId);

    /**
     * 根据学生 ID 删除选课信息
     * @param studentId
     */
    void deleteByStudentId(Integer studentId);

    /**
     * 获取课程数
     * @return
     */
    int getSelectedCourseNum();

    /**
     * 分页查询选课
     * @param params
     * @return
     */
    List<SelectedCourse> page(Map<String, Object> params);

    /**
     * 根据课程 ID 查询课程已选人数
     * @return
     */
    int getStuCount(int courseId);


    /**
     * 查询学生 Id 分页集合
     * @param courseId
     * @return
     */
    List<Integer> getStudentList(Map<String, Object> params);

    /**
     * 查询学生已选课程 ID 集合
     * @param studentId
     * @return
     */
    List<Integer> getCourseIdByStudentId(int studentId);

    /**
     * 删除选课信息
     * @param selectedCourse
     */
    void deleteByCourseIdAndStudentId(@Param("selectedCourse") SelectedCourse selectedCourse);
}