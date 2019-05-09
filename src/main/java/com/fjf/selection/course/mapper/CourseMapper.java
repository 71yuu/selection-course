package com.fjf.selection.course.mapper;

import com.fjf.selection.course.domain.Course;
import java.util.List;
import java.util.Map;

public interface CourseMapper {
    int deleteByPrimaryKey(Integer courseid);

    int insert(Course record);

    Course selectByPrimaryKey(Integer courseid);

    List<Course> selectAll();

    int updateByPrimaryKey(Course record);

    /**
     * 获取课程总数量
     * @return
     */
    int getCourseNum();

    /**
     * 分页查询
     * @param params
     * @return
     */
    List<Course> page(Map<String, Object> params);

    /**
     * 查询教师是否有授课信息
     * @param userId
     * @return
     */
    int findTeacherById(Integer userId);

    /**
     * 查看该院系是否有课程
     */
    int findCollegeById(Integer collegeId);

    /**
     * 查询教师课程数
     * @param teacherId
     * @return
     */
    int getByTeacherId(int teacherId);

    /**
     * 教师课程分页查询
     * @param params
     * @return
     */
    List<Course> coursePageByTeacherId(Map<String, Object> params);

    /**
     * 根据已选课程 ID 查询未选课程分页
     * @param params
     * @return
     */
    List<Course> getCourseListByCourseId(Map<String, Object> params);

    /**
     * 获取未选课程总数
     * @param selectedCourseIdList
     * @return
     */
    int getOptionalNum(List<Integer> selectedCourseIdList);

    /**
     * 获取已选课程
     * @param params
     * @return
     */
    List<Course> getRetreatCourseListByCourseId(Map<String, Object> params);
}