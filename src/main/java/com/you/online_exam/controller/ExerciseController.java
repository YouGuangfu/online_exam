package com.you.online_exam.controller;


import com.you.online_exam.dao.ExerciseFront;
import com.you.online_exam.entity.Exercise;
import com.you.online_exam.service.ExerciseService;
import com.you.online_exam.service.SubjectService;
import com.you.online_exam.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
@RequestMapping("/exercises")
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

    @GetMapping("/list")
    public String exercise(@RequestParam(value = "type",required = false) String type,
                           @PageableDefault(sort = {"id"},direction = Sort.Direction.ASC)Pageable pageable,
                           HttpServletRequest request, Model model){
        RequestUtils.setFrontUserInfo(model,request);
        model.addAttribute("subjects",subjectService.getAllSubjects());

        ExerciseFront exerciseFront = exerciseService.getAllByType(type,pageable);
        Long count = exerciseFront.count/pageable.getPageSize() + 1;
        model.addAttribute("exercises",exerciseFront.exercisePage);
        model.addAttribute("count",count);
        model.addAttribute("exerciseType",type);
        model.addAttribute("currentPage",pageable.getPageNumber() +1);

        return "exercise";
    }

}

