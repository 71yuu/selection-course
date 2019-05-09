package com.fjf.selection.course.controller;

import com.fjf.selection.course.commons.dto.PageInfo;
import com.fjf.selection.course.domain.Course;
import com.fjf.selection.course.domain.Teacher;
import com.fjf.selection.course.domain.UserLogin;
import com.fjf.selection.course.service.TeacherService;
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
 * @Classname TeacherController
 * @Date 2019/5/8 23:36
 * @Created by Fjf
 */
@Controller
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 跳转到教师管理首页
     * @return
     */
    @GetMapping("index")
    public ModelAndView index(ModelAndView modelAndView, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("userLogin");
        Teacher teacher = teacherService.getById(Integer.parseInt(userLogin.getUserName()));
        session.setAttribute("teacher", teacher);
        modelAndView.setViewName("teacher/index");
        return modelAndView;
    }

    /**
     * 教师课程分页查询
     * @param request
     * @return
     */
    @GetMapping("course/page")
    @ResponseBody
    public PageInfo<Course> selectedCourse(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));

        Map<String, Object> params = new HashMap<>();
        params.put("page", start);
        params.put("pageSize", length);
        params.put("teacherId", teacherId);

        PageInfo<Course> pageInfo = teacherService.coursePage(params);
        pageInfo.setDraw(draw == null ? 0 : Integer.parseInt(draw));
        return pageInfo;
    }

    /**
     * 跳转到修改密码页面
     * @return
     */
    @GetMapping("updatePassword")
    public String updatePasswordPage() {
        return "teacher/updatePwd";
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
        teacherService.updatePassword(userLogin);
        session.invalidate();
        return "redirect:/login";
    }



}
