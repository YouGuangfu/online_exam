package com.you.online_exam.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author 尤广富 (Online Class)
 * @create 2019-03-26 20:22
 **/
@RestController
public class TestController {
    //访问/hello或者/hi任何一个地址，都会返回一样的结果
    @RequestMapping(value = {"/hello","/hi"},method = RequestMethod.GET)
    public String say(){
        return "hi you!!!";
    }
}
