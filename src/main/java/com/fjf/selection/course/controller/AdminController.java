package com.fjf.selection.course.controller;

import com.fjf.selection.course.commons.dto.*;
import com.fjf.selection.course.domain.College;
import com.fjf.selection.course.domain.Course;
import com.fjf.selection.course.domain.Student;
import com.fjf.selection.course.domain.Teacher;
import com.fjf.selection.course.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员
 * @Classname AdminController
 * @Date 2019/5/6 20:57
 * @Created by Fjf
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SelectedCourseService selectedCourseService;

    @ModelAttribute
    public Course getCourseById(Integer courseId) {
        Course course = null;
        // 新增
        if (courseId != null) {
            course = courseService.getById(courseId);
        }

        // 编辑
        else {
            course = new Course();
        }
        return course;
    }

    /**
     * 跳转到管理员首页
     * @return
     */
    @GetMapping({"index", "course/list"})
    public String admin() {
        return "admin/course/index";
    }

    /**
     * 分页查询
     * @param request
     * @return
     */
    @GetMapping("course/page")
    @ResponseBody
    public PageInfo<CourseVo> page(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));

        Map<String, Object> params = new HashMap<>();
        params.put("page", start);
        params.put("pageSize", length);

        PageInfo<CourseVo> pageInfo = courseService.page(params);
        pageInfo.setDraw(draw == null ? 0 : Integer.parseInt(draw));
        return pageInfo;
    }

    /**
     * 跳转到课程表单页
     * @param modelAndView
     * @return
     */
    @GetMapping("course/form")
    public ModelAndView form(ModelAndView modelAndView) {
        modelAndView.setViewName("admin/course/form");

        // 封装所有教师
        List<Teacher> teacherList = teacherService.teacherList();

        // 封装所有院系
        List<College> collegeList = collegeService.collegeList();

        modelAndView.addObject("teacherList", teacherList);
        modelAndView.addObject("collegeList", collegeList);

        return modelAndView;
    }

    /**
     * 保存课程信息
     * @return
     */
    @PostMapping("course/save")
    public String save(Course course) {
        courseService.save(course);
        return "redirect:/admin/course/list";
    }

    /**
     * 删除课程信息
     * @param courseId
     * @return
     */
    @GetMapping("course/delete")
    public String delete(Integer courseId) {
        courseService.deleteById(courseId);
        return "redirect:/admin/course/list";
    }

    @ModelAttribute
    public Teacher getTeacherById(Integer teacherId) {
            Teacher teacher = null;
        // 新增
        if (teacherId != null) {
            teacher = teacherService.getById(teacherId);
        }

        // 编辑
        else {
            teacher = new Teacher();
        }
        return teacher;
    }

    /**
     * 跳转到教师列表
     * @return
     */
    @GetMapping("teacher/list")
    public String teacherIndex() {
        return "admin/teacher/index";
    }

    /**
     * 分页查询
     * @param request
     * @return
     */
    @GetMapping("teacher/page")
    @ResponseBody
    public PageInfo<TeacherVo> teacherPage(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));

        Map<String, Object> params = new HashMap<>();
        params.put("page", start);
        params.put("pageSize", length);

        PageInfo<TeacherVo> pageInfo = teacherService.page(params);
        pageInfo.setDraw(draw == null ? 0 : Integer.parseInt(draw));
        return pageInfo;
    }

    /**
     * 跳转到教师表单页
     * @param modelAndView
     * @return
     */
    @GetMapping("teacher/form")
    public ModelAndView teacherForm(ModelAndView modelAndView) {
        modelAndView.setViewName("admin/teacher/form");

        // 封装所有院系
        List<College> collegeList = collegeService.collegeList();

        modelAndView.addObject("collegeList", collegeList);

        return modelAndView;
    }

    /**
     * 保存教师信息
     * @return
     */
    @PostMapping("teacher/save")
    public String teacherSave(Teacher teacher) {
        teacherService.save(teacher);
        return "redirect:/admin/teacher/list";
    }

    /**
     * 删除教师信息
     * @param userId
     * @return
     */
    @GetMapping("teacher/delete")
    public String teacherDelete(Integer userId, Model model) {
        boolean flag = teacherService.deleteById(userId);

        // 老师有课程正在教授,不能删除
        if (!flag) {
            model.addAttribute("message", "该老师有课程正在教授,不能删除");
        }

        return "/admin/teacher/index";
    }

    @ModelAttribute
    public College getCollegeById(Integer collegeId) {
        College college = null;
        // 新增
        if (collegeId != null) {
            college = collegeService.getById(collegeId);
        }

        // 编辑
        else {
            college = new College();
        }
        return college;
    }

    /**
     * 跳转到院系列表
     * @return
     */
    @GetMapping("college/list")
    public String collegeIndex() {
        return "admin/college/index";
    }

    /**
     * 分页查询
     * @param request
     * @return
     */
    @GetMapping("college/page")
    @ResponseBody
    public PageInfo<College> collegePage(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));

        Map<String, Object> params = new HashMap<>();
        params.put("page", start);
        params.put("pageSize", length);

        PageInfo<College> pageInfo = collegeService.page(params);
        pageInfo.setDraw(draw == null ? 0 : Integer.parseInt(draw));
        return pageInfo;
    }

    /**
     * 跳转到院系表单页
     * @return
     */
    @GetMapping("college/form")
    public String collegeForm() {
        return "admin/college/form";
    }

    /**
     * 保存院系信息
     * @return
     */
    @PostMapping("college/save")
    public String collegeSave(College college) {
        collegeService.save(college);
        return "redirect:/admin/college/list";
    }

    /**
     * 删除院系信息
     * @param collegeId
     * @return
     */
    @GetMapping("college/delete")
    public String collegeDelete(Integer collegeId, Model model) {
        boolean flag = collegeService.deleteById(collegeId);
        if (!flag) {
            model.addAttribute("message", "该院系目前还有课程、教师、或者学生，不能删除！");
        }
        return "/admin/college/index";
    }

    @ModelAttribute
    public Student getStudentById(Integer studentId) {
        Student student = null;

        // 新增
        if (studentId != null) {
            student = studentService.getById(studentId);
        }

        // 编辑
        else {
            student = new Student();
        }
        return student;
    }

    /**
     * 跳转到学生列表
     * @return
     */
    @GetMapping("student/list")
    public String studentIndex() {
        return "admin/student/index";
    }

    /**
     * 分页查询
     * @param request
     * @return
     */
    @GetMapping("student/page")
    @ResponseBody
    public PageInfo<StudentVo> studentPage(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));

        Map<String, Object> params = new HashMap<>();
        params.put("page", start);
        params.put("pageSize", length);

        PageInfo<StudentVo> pageInfo = studentService.page(params);
        pageInfo.setDraw(draw == null ? 0 : Integer.parseInt(draw));
        return pageInfo;
    }

    /**
     * 跳转到学生表单页
     * @return
     */
    @GetMapping("student/form")
    public ModelAndView studentForm(ModelAndView modelAndView) {
        modelAndView.setViewName("admin/student/form");

        // 封装所有院系
        List<College> collegeList = collegeService.collegeList();

        modelAndView.addObject("collegeList", collegeList);

        return modelAndView;
    }

    /**
     * 保存学生信息
     * @return
     */
    @PostMapping("student/save")
    public String studentSave(Student student) {
        studentService.save(student);
        return "redirect:/admin/student/list";
    }

    /**
     * 删除学生信息
     * @param userId
     * @return
     */
    @GetMapping("student/delete")
    public String studentDelete(Integer userId, Model model) {
        studentService.deleteById(userId);
        return "/admin/student/index";
    }

    /**
     * 跳转到选课列表
     * @param modelAndView
     * @return
     */
    @GetMapping("selectedcourse/index")
    public ModelAndView selectedCourse(ModelAndView modelAndView) {
        modelAndView.setViewName("admin/selectedcourse/index");
        return modelAndView;
    }

    /**
     * 分页查询
     * @param request
     * @return
     */
    @GetMapping("selectedcourse/page")
    @ResponseBody
    public PageInfo<SelectedCourseVo> selectedCourse(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));

        Map<String, Object> params = new HashMap<>();
        params.put("page", start);
        params.put("pageSize", length);

        PageInfo<SelectedCourseVo> pageInfo = selectedCourseService.page(params);
        pageInfo.setDraw(draw == null ? 0 : Integer.parseInt(draw));
        return pageInfo;
    }

    /**
     * 跳转到学生详情页
     * @param modelAndView
     * @return
     */
    @GetMapping("selectedcourse/detail")
    public ModelAndView selectCourseDetail(Integer courseId, ModelAndView modelAndView) {
        // 根据课程 ID 查询课程
        Course course = courseService.getById(courseId);
        modelAndView.addObject("course", course);

        modelAndView.setViewName("admin/selectedcourse/detail");
        return modelAndView;
    }

    /**
     * 课程学生详情
     * @param request
     * @return
     */
    @GetMapping("selectedcourse/detailpage")
    @ResponseBody
    public PageInfo<CourseStudentVo> selectedCoursePage(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        Map<String, Object> params = new HashMap<>();
        params.put("page", start);
        params.put("pageSize", length);
        params.put("courseId", courseId);

        PageInfo<CourseStudentVo> pageInfo = selectedCourseService.studentPage(params);
        pageInfo.setDraw(draw == null ? 0 : Integer.parseInt(draw));
        return pageInfo;
    }



}
