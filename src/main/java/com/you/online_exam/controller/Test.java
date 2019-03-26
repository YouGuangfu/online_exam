package com.you.online_exam.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * controller测试
 *
 * @author 尤广富 (Online Class)
 * @create 2019-03-26 16:38
 **/
@Controller
public class Test {
    @RequestMapping("/test")
    public String test(Map<Object,Object> map, HttpServletRequest request, HttpServletResponse response){
        map.put("yemian","1");
        return "test";
    }
}
