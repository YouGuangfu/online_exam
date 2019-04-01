package com.you.online_exam.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.you.online_exam.dao.PaperDao;
import com.you.online_exam.dao.UserDao;
import com.you.online_exam.entity.Exercise;
import com.you.online_exam.entity.User;
import com.you.online_exam.service.ExerciseService;
import com.you.online_exam.service.PaperService;
import com.you.online_exam.service.TeacherStudentService;
import com.you.online_exam.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * 学生controller
 *
 * @author 尤广富 (Online Class)
 * @create 2019-03-30 11:28
 **/
@Controller
@RequestMapping("/user/stu")
public class StudentController {

    @Autowired
    TeacherStudentService teacherStudentService;
    @Autowired
    ExerciseService exerciseService;
    @Autowired
    PaperService paperService;

//    @GetMapping("/exercises")
//    public String exercises(HttpServletRequest request, Model model){
//        UserDao userDao = RequestUtils.getUserAuxiliaryFromReq(request);
//
//        Set<Exercise> exerciseSet = teacherStudentService.getExerciseConllection(userDao.getId());
//        model.addAttribute("exercises",exerciseSet);
//        return "stu/exercise_page";
//    }

    @GetMapping("/exams")
    public String exams(HttpServletRequest request, Model model){
        UserDao userDao = RequestUtils.getUserAuxiliaryFromReq(request);
        List<PaperDao> paperDaoList = teacherStudentService.getPapers(userDao.getId());
        model.addAttribute("papers",paperDaoList);
        return "stu/exam_page";
    }
//    @GetMapping("/collect_exercises/add/{id}")
//    public String addExercisesCollection(@PathVariable Long id,HttpServletRequest request){
//        UserDao userDao = RequestUtils.getUserAuxiliaryFromReq(request);
//        teacherStudentService.addExerciseCollections(userDao.getId(),id);
//        return "redirect:/exercises/list/type=all";
//    }

    @GetMapping("/papers/{id}/join")
    public String joinExam(@PathVariable("id") Long id,Model model){
        List<Exercise> exerciseList = exerciseService.getAllExercisesByPaperId(id);
        int time =paperService.getPaperById(id).getAnswerTime();
        model.addAttribute("time",time);
        model.addAttribute("paperId",id);
        model.addAttribute("exercises",exerciseList);
        return "stu/answer_paper";
    }

    @PostMapping("/papers/{id}")
    public String answerPaper(@PathVariable("id") Long id,String answers,HttpServletRequest request){
        UserDao userDao = RequestUtils.getUserAuxiliaryFromReq(request);
        teacherStudentService.answerPaper(userDao.getId(),id,answers);
        return "redirect:/user/stu/exams";
    }


}
