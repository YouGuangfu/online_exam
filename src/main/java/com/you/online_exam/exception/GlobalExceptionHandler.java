package com.you.online_exam.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理类
 *
 * @author 尤广富 (Online Class)
 * @create 2019-03-27 10:50
 **/
@ControllerAdvice(basePackages = "com.you.*")
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandlerProd(Exception e, Model model) {
        log.info(e.getLocalizedMessage());
        model.addAttribute("status",500);
        model.addAttribute("title","sorry，服务器出错了。");
        model.addAttribute("error",e.getLocalizedMessage());
        return new ModelAndView("error");
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoPageException.class)
    public String noPageError(Exception e, Model model) {
        log.info(e.getLocalizedMessage());
        model.addAttribute("status",404);
        model.addAttribute("title","sorry，您访问的页面不存在");
        model.addAttribute("error",e.getLocalizedMessage());
        return "error";
    }
}
