package com.you.online_exam.controller;


import com.you.online_exam.dao.ExerciseDao;
import com.you.online_exam.dao.PaperDao;
import com.you.online_exam.dao.UserDao;
import com.you.online_exam.entity.Exercise;
import com.you.online_exam.entity.User;
import com.you.online_exam.exception.CommonException;
import com.you.online_exam.service.ExerciseService;
import com.you.online_exam.service.PaperService;
import com.you.online_exam.service.SubjectService;
import com.you.online_exam.service.TeacherStudentService;
import com.you.online_exam.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
@RequestMapping("/user/tea")
public class TeacherController {

    @Autowired
    SubjectService subjectService;
    @Autowired
    PaperService paperService;
    @Autowired
    ExerciseService exerciseService;
    @Autowired
    TeacherStudentService teacherStudentService;

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

    @GetMapping("/manager_paper")
    public String managerPaper(Model model,HttpServletRequest request){
        UserDao userDao = RequestUtils.getUserAuxiliaryFromReq(request);
        /**
         * 根据当前用户的id 查找试卷信息
         */
        model.addAttribute("papers",paperService.getPapersByTeacherId(userDao.getId()));
        return "tea/manager_paper";
    }

    @GetMapping("/papers/{id}/edit")
    public String editPager(@PathVariable Long id,Model model){
        model.addAttribute("paperId",id);
        model.addAttribute("subjectId",paperService.getPaperById(id).getSubjectId());
        List<Exercise> exercises = exerciseService.getAllExercisesByPaperId(id);
        model.addAttribute("exercises",exercises);
        return "tea/edit_paper";
    }
    @GetMapping("/papers/{id}/delete")
    public String deletePaper(@PathVariable Long id,HttpServletRequest request){
        UserDao userDao = RequestUtils.getUserAuxiliaryFromReq(request);
        if(!paperService.delPaper(id,userDao.getId())){
            throw new CommonException("删除失败");
        }
        return "redirect:/user/tea/manager_paper";
    }
    @PostMapping("exercises")
    public String addExercise(@Valid ExerciseDao exerciseDao){
        if (exerciseDao == null){
            return "redirect:/user/tea/papers/"+exerciseDao.getPaperId()+"/edit";
        }
        if (!exerciseService.addExercise(exerciseDao)){
            throw new CommonException("参数错误");
        }
        return "redirect:/user/tea/papers/"+exerciseDao.getPaperId()+"/edit";
    }

    @GetMapping("/add_subject")
    public String addSubject(Model model){
        StringBuilder sb = new StringBuilder();
        subjectService.getAllSubjects().forEach(t->sb.append(t.getName()).append(" "));
        model.addAttribute("subjects",sb.toString());
        return "tea/add_subject";
    }
    @PostMapping("/subjects")
    public String addSubjects(@RequestParam("name") String name){
        if (StringUtils.isEmpty(name)){
//            throw new CommonException("添加科目失败");
            return "redirect:/user/tea/add_subject";
        }
        if (!subjectService.addSubject(name)){
//            throw new CommonException("添加失败");
            //添加提示信息，或者转到其他模板
            return "redirect:/user/tea/add_subject";
        }
        return "redirect:/user/tea/add_subject";
    }

    @GetMapping("/add_student")
    public String addStudent(HttpServletRequest request,Model model){
        UserDao userDao = RequestUtils.getUserAuxiliaryFromReq(request);
        //当前存在的学生
        List<User> existStudents = teacherStudentService.getAllStudentsByTeacherId(userDao.getId());
        //所有学生
        List<User> alltudents = teacherStudentService.getAllStudents();
        StringBuilder stringBuilder = new StringBuilder();
        existStudents.forEach(t -> stringBuilder.append(t.getName()).append(" "));
        model.addAttribute("studentNames",stringBuilder.toString());
        model.addAttribute("students",alltudents);
        return "tea/add_student";
    }

    /**
     * 根据传过来的学生id，添加学生
     * @param id
     * @param request
     * @return
     */
    @PostMapping("/students")
    public String addStudents(@RequestParam("id") Long id,HttpServletRequest request){
        if (id == null){
            throw new CommonException("添加学生发生错误");
        }
        UserDao userDao = RequestUtils.getUserAuxiliaryFromReq(request);
        teacherStudentService.addStudent(userDao.getId(),id);
        return "redirect:/user/tea/add_student";
    }

    @GetMapping("/correct_paper")
    public String paperCorrect(HttpServletRequest request,Model model){
        UserDao userDao = RequestUtils.getUserAuxiliaryFromReq(request);
        List<PaperDao> paperDaoList = teacherStudentService.getPapersByTeacherId(userDao.getId());
        model.addAttribute("papers",paperDaoList);
        return "tea/correct_paper";
    }

    @GetMapping("papers/{id}/score")
    public String scorePage(@PathVariable Long id,@RequestParam("student") Long studentId,Model model){
        List<Exercise> exercises = exerciseService.getExercises(id,studentId);

        model.addAttribute("exercises",exercises);
        model.addAttribute("paperId",id);
        model.addAttribute("student",studentId);
        return "tea/score_page";
    }

    @PostMapping("/paper/{id}/score")
    public String doScorePage(@PathVariable Long id,@RequestParam("student") Long studentId, @RequestParam String scores){
        teacherStudentService.testPapers(id,studentId,scores);
        return "redirect:/user/tea/correct_paper";
    }

}

