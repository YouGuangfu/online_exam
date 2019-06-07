package com.you.online_exam.controller;


import com.you.online_exam.entity.User;
import com.you.online_exam.service.UserService;
import com.you.online_exam.utils.CommonUtils;
import com.you.online_exam.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 尤广富
 * @since 2019-03-26
 */
@Controller
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request){
        RequestUtils.setFrontUserInfo(model,request);
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login.do")
    public String loginToSys(User user, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {

        boolean result = false;
        try {
            result = userService.login(user,httpServletRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (result){
            //已经设置好session 直接重定向到主页
            return "redirect:/";
        }
        //重新登录
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        out.print("<script language=\"javascript\"> confirm('登录失败！账号或密码错误！')");
        out.print("</script>");

        return "redirect:/login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register.do")
    public String userRegister(User user, HttpServletRequest request) {

        if (CommonUtils.isEmpty(user.getName(), user.getPassword())) {
            //前端提示 先重定向到注册
            return "redirect:/register";
        }
        //判断两次密码是否一致
        Long passBefore = Long.parseLong(request.getParameter("passBefore"));
        Long passRepeat = Long.parseLong(request.getParameter("passRepeat"));
        if (!passBefore.equals(passRepeat)) {
            return "redirect:/register";
        }
        //判断用户是否存在
        boolean isExist = userService.userExist(user);
        if (isExist) {
            return "redirect:/register";
        }
        userService.userRegister(user);
        //注册完后登录
        return "redirect:/login";
    }

    @GetMapping("user/logout")
    public String logOut(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        //删除当前用户
        session.removeAttribute("cur_user");
        //重定向到主页
        return "redirect:/";
    }

}

