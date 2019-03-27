package com.you.online_exam.controller;


import com.you.online_exam.entity.Exercise;
import com.you.online_exam.mapper.ExerciseMapper;
import com.you.online_exam.service.ExerciseService;
import com.you.online_exam.service.SubjectService;
import com.you.online_exam.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    @Autowired
    SubjectService subjectService;
    @Autowired
    ExerciseService exerciseService;
    @GetMapping("/{id}")
    public String getExercise(@PathVariable("id") Long id, Model model, HttpServletRequest request){
        RequestUtils.setFrontUserInfo(model,request);
        model.addAttribute("subjects",subjectService.getAllSubjects());

        Exercise exercise = exerciseService.getExercise(id);

        if (exercise == null){

        }
        model.addAttribute("exe", exercise);
        return "exe_detail";
    }
}

