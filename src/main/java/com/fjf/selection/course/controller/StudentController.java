package com.fjf.selection.course.controller;

import com.fjf.selection.course.commons.dto.CourseVo;
import com.fjf.selection.course.commons.dto.PageInfo;
import com.fjf.selection.course.domain.Course;
import com.fjf.selection.course.domain.Student;
import com.fjf.selection.course.domain.UserLogin;
import com.fjf.selection.course.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname Student
 * @Date 2019/5/9 8:10
 * @Created by Fjf
 */
@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 跳转到学生管理首页
     * @return
     */
    @GetMapping("index")
    public String index(HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("userLogin");
        Student student = studentService.getById(Integer.parseInt(userLogin.getUserName()));
        session.setAttribute("student", student);
        return "student/index";
    }

    @GetMapping("course/optional/page")
    @ResponseBody
    public PageInfo<CourseVo> optionalPage(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));

        UserLogin userLogin = (UserLogin) request.getSession().getAttribute("userLogin");
        int studentId = Integer.parseInt(userLogin.getUserName());

        Map<String, Object> params = new HashMap<>();
        params.put("page", start);
        params.put("pageSize", length);
        params.put("studentId", studentId);

        PageInfo<CourseVo> pageInfo = studentService.optionalPage(params);
        pageInfo.setDraw(draw == null ? 0 : Integer.parseInt(draw));
        return pageInfo;
    }

    /**
     * 选课
     * @param modelAndView
     * @return
     */
    @GetMapping("course/select")
    public ModelAndView selectCourse(Integer courseId, HttpSession session, ModelAndView modelAndView) {
        UserLogin userLogin = (UserLogin) session.getAttribute("userLogin");
        int studentId = Integer.parseInt(userLogin.getUserName());
        studentService.selectCourse(studentId, courseId);

        modelAndView.addObject("message", "选课成功!");
        modelAndView.setViewName("student/selectedCourse");
        return modelAndView;
    }

    /**
     * 跳转到已选课程页面
     * @return
     */
    @GetMapping("course/selectedCourse")
    public String selectedCoursePage() {
        return"student/selectedCourse";
    }

    /**
     * 已选课程分页查询
     */
    @GetMapping("course/retreat/page")
    @ResponseBody
    public PageInfo<CourseVo> retreatPage(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));

        UserLogin userLogin = (UserLogin) request.getSession().getAttribute("userLogin");
        int studentId = Integer.parseInt(userLogin.getUserName());

        Map<String, Object> params = new HashMap<>();
        params.put("page", start);
        params.put("pageSize", length);
        params.put("studentId", studentId);

        PageInfo<CourseVo> pageInfo = studentService.retreatPage(params);
        pageInfo.setDraw(draw == null ? 0 : Integer.parseInt(draw));
        return pageInfo;
    }

    /**
     * 退课
     * @param courseId
     * @param session
     * @param modelAndView
     * @return
     */
    @GetMapping("course/retreat")
    public ModelAndView retreatCourse(Integer courseId, HttpSession session, ModelAndView modelAndView) {
        UserLogin userLogin = (UserLogin) session.getAttribute("userLogin");
        int studentId = Integer.parseInt(userLogin.getUserName());
        studentService.retreatCourse(studentId, courseId);

        modelAndView.addObject("message", "退课成功!");
        modelAndView.setViewName("student/selectedCourse");
        return modelAndView;
    }

    /**
     * 跳转到修改密码页
     * @return
     */
    @GetMapping("updatePassword")
    public String updatePasswordPage() {
        return "student/updatePwd";
    }

    /**
     * 修改密码
     * @param session
     * @return
     */
    @PostMapping("update/password")
    public String updatePassword(HttpSession session, String password) {
        UserLogin userLogin = (UserLogin) session.getAttribute("userLogin");
        userLogin.setPassword(password);
        studentService.updatePassword(userLogin);
        session.invalidate();
        return "redirect:/login";
    }

}
