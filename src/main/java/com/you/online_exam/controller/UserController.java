package com.you.online_exam.controller;


import com.you.online_exam.service.UserService;
import com.you.online_exam.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 尤广富
 * @since 2019-03-26
 */
@Controller
@RequestMapping("online")
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

}

