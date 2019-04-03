package com.you.online_exam.controller;


import com.you.online_exam.entity.Exercise;
import com.you.online_exam.entity.Paper;
import com.you.online_exam.service.ExerciseService;
import com.you.online_exam.service.PaperService;
import com.you.online_exam.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 尤广富
 * @since 2019-03-26
 */
@Controller
@RequestMapping("/papers")
public class PaperController {

    @Autowired
    PaperService paperService;
    @Autowired
    ExerciseService exerciseService;

    @GetMapping
    public String papers(HttpServletRequest request, Model model){
        RequestUtils.setFrontUserInfo(model,request);
        List<Paper> paperList = paperService.getAllPapers();
        model.addAttribute("papers",paperList);
        return "paper";
    }

    @GetMapping("/{id}")
    public String paperDetail(@PathVariable("id") Long id,Model model,HttpServletRequest request){
        RequestUtils.setFrontUserInfo(model,request);
        List<Exercise> exerciseList = exerciseService.getAllExercisesByPaperId(id);
        model.addAttribute("exercises",exerciseList);
        return "paper_detail";
    }
}

