package com.you.online_exam.controller;


import com.you.online_exam.dao.UserDao;
import com.you.online_exam.exception.CommonException;
import com.you.online_exam.service.PaperService;
import com.you.online_exam.service.SubjectService;
import com.you.online_exam.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

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
@RequestMapping("/user/tea")
public class TeacherController {

    @Autowired
    SubjectService subjectService;
    @Autowired
    PaperService paperService;

    /**
     * 添加试卷路由
     * @param model
     * @return
     */
    @GetMapping("/add_paper")
    public String pagerAdd(Model model){
        model.addAttribute("subjects",subjectService.getAllSubjects());
        return "tea/add_paper";
    }

    @PostMapping("/papers")
    public String addPaper(@RequestParam("title") String title,
                           @RequestParam("subject") Long subject,
                           @RequestParam("answer_time") Integer answerTime, HttpServletRequest request){
        if (StringUtils.isEmpty(title) || subject == null){
            throw new CommonException("参数错误");
        }
        UserDao userDao = RequestUtils.getUserAuxiliaryFromReq(request);
        if(!paperService.addPaper(title,subject,answerTime,userDao.getId())){
            throw new CommonException("参数错误");
        }
        return "redirect:/user/tea/manager_paper";
    }


    @GetMapping("/add_subject")
    public String addSubject(Model model){
        StringBuilder sb = new StringBuilder();
        subjectService.getAllSubjects().forEach(t->sb.append(t.getName()).append(" "));
        model.addAttribute("subjects",sb.toString());
        return "tea/add_subject";
    }


}

