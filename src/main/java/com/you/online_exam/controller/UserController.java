package com.you.online_exam.controller;


import com.you.online_exam.entity.Exercise;
import com.you.online_exam.entity.User;
import com.you.online_exam.service.UserService;
import com.you.online_exam.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String loginToSys(User user,HttpServletRequest httpServletRequest){

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
        return "redirect:/login";

    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register.do")
    public String userRegister(User user){

        if (!userService.userExist(user)){

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

