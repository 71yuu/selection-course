package com.fjf.selection.course.service.impl;

import com.fjf.selection.course.commons.dto.PageInfo;
import com.fjf.selection.course.domain.College;
import com.fjf.selection.course.mapper.CollegeMapper;
import com.fjf.selection.course.mapper.CourseMapper;
import com.fjf.selection.course.mapper.StudentMapper;
import com.fjf.selection.course.mapper.TeacherMapper;
import com.fjf.selection.course.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Classname CollegeServiceImpl
 * @Date 2019/5/7 13:38
 * @Created by Fjf
 */
@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<College> collegeList() {
        return collegeMapper.selectAll();
    }

    @Override
    public College getById(Integer collegeId) {
        return collegeMapper.selectByPrimaryKey(collegeId);
    }

    @Override
    public PageInfo<College> page(Map<String, Object> params) {
        PageInfo<College> pageInfo = new PageInfo<>();

        int count = collegeMapper.getCollegeNum();
        List<College> colleges = collegeMapper.page(params);

        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(colleges);
        return pageInfo;
    }

    @Override
    public void save(College college) {
        // 新增
        if (college.getCollegeId() != null) {
            collegeMapper.updateByPrimaryKey(college);
        }
        // 删除
        else {
            collegeMapper.insert(college);
        }
    }

    @Override
    public boolean deleteById(Integer collegeId) {
        // 查看该院系是否有课程
        int courseCount = courseMapper.findCollegeById(collegeId);
        // 查看该院系是否有教师
        int teacherCount = teacherMapper.findCollegeById(collegeId);
        // 查看该院系是否有学生
        int studentCount = studentMapper.findCollegeById(collegeId);

        // 该院系有课程、教师、或者学生，不能删除
        if (courseCount > 0 || teacherCount > 0 || studentCount > 0) {
            return false;
        }
        else {
          collegeMapper.deleteByPrimaryKey(collegeId);
          return true;
        }
    }
}
