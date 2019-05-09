package com.fjf.selection.course.controller;

import com.fjf.selection.course.domain.UserLogin;
import com.fjf.selection.course.service.UserLoginService;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
/**
 * @Classname LoginController
 * @Date 2019/5/6 15:16
 * @Created by Fjf
 */
@Controller
public class LoginController {

    @Autowired
    private UserLoginService userloginService;

    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping({"", "/login"})
    public String login() {
        return "login";
    }

    /**
     * 登录操作
     * @return
     */
    @PostMapping("login")
    public ModelAndView login(@RequestParam(required = true) String userName,
                              @RequestParam(required = true) String password,
                              @RequestParam(required = true) String verification,
                              HttpSession session,
                              ModelAndView modelAndView) {
        // 判断验证码是否正确
        String vCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (!vCode.equalsIgnoreCase(verification)) {
            modelAndView.setViewName("login");
            modelAndView.addObject("message", "验证码不正确");
            return modelAndView;
        }

        // 登录逻辑
        UserLogin userLogin = userloginService.login(userName, password);

        // 登录失败
        if (userLogin == null) {
            modelAndView.setViewName("login");
            modelAndView.addObject("message", "学号/工号或密码输入不正确");
        }

        // 登录成功
        else {
            // 管理员登录
            if (userLogin.getRole() == 0) {
                modelAndView.setViewName("redirect:admin/index");
            }

            // 教师登录
            else if (userLogin.getRole() == 1) {
                modelAndView.setViewName("redirect:teacher/index");
            }

            // 学生登录
            else if (userLogin.getRole() == 2) {
                modelAndView.setViewName("redirect:student/index");
            }
            session.setAttribute("userLogin", userLogin);
        }
        return modelAndView;
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
