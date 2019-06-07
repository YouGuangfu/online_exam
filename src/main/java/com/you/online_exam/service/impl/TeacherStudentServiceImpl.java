package com.you.online_exam.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.you.online_exam.dao.AnswerDao;
import com.you.online_exam.dao.PaperDao;
import com.you.online_exam.dao.ScoreDao;
import com.you.online_exam.entity.*;
import com.you.online_exam.mapper.*;
import com.you.online_exam.service.TeacherStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 尤广富
 * @since 2019-03-26
 */
@Service
public class TeacherStudentServiceImpl extends ServiceImpl<TeacherStudentMapper, TeacherStudent> implements TeacherStudentService {

    private static final int ROLE_STUDENT = 1;
    private static final int ROLE_TEACHER = 2;

    @Autowired
    UserMapper userMapper;

    @Autowired
    TeacherStudentMapper teacherStudentMapper;
    @Autowired
    PaperMapper paperMapper;
    @Autowired
    UserPaperAnswerMapper userPaperAnswerMapper;
    @Autowired
    SubjectMapper subjectMapper;
    @Autowired
    ExerciseMapper exerciseMapper;
    @Autowired
    UserPaperScoreMapper userPaperScoreMapper;
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public List<User> getAllStudents() {
        return userMapper.selectList(null);
    }

    @Override
    public List<User> getAllStudentsByTeacherId(Long teacherId) {
        EntityWrapper<TeacherStudent> teacherStudentEntityWrapper = new EntityWrapper<>();
        teacherStudentEntityWrapper.eq("teacher_id",teacherId);
        /**
         * lambda表达式，先获取teacherStudent ，从中获取studentid
         * 根据studentId查找List<User>
         */
        return teacherStudentMapper.selectList(teacherStudentEntityWrapper)
                .stream()
                .map(t -> userMapper.selectOne(new User(t.getStudentId())))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean addStudent(Long teacherId, Long studentId) {
        //先查询是否存在
        EntityWrapper<TeacherStudent> teacherStudentEntityWrapper = new EntityWrapper<>();
        teacherStudentEntityWrapper.eq("teacher_id",teacherId);
        teacherStudentEntityWrapper.eq("student_id",studentId);
        if(teacherStudentMapper.selectCount(teacherStudentEntityWrapper)>0){
            return false;
        }
        TeacherStudent teacherStudent = new TeacherStudent();
        teacherStudent.setStudentId(studentId);
        teacherStudent.setTeacherId(teacherId);
        return teacherStudentMapper.insert(teacherStudent)>0;
    }

    @Override
    public List<PaperDao> getPapersByTeacherId(Long teacherId) {
        List<PaperDao> paperDaoList = new ArrayList<>();
        EntityWrapper<Paper> paperEntityWrapper = new EntityWrapper<>();
        paperEntityWrapper.eq("owner_id",teacherId);
        List<Paper> paperList = paperMapper.selectList(paperEntityWrapper);
        EntityWrapper<TeacherStudent> teacherStudentEntityWrapper = new EntityWrapper<>();
        teacherStudentEntityWrapper.eq("teacher_id",teacherId);
        List<TeacherStudent> teacherStudentList = teacherStudentMapper.selectList(teacherStudentEntityWrapper);

        for (Paper paper:paperList) {
            for (TeacherStudent teacherStudent:teacherStudentList) {
                EntityWrapper<UserPaperAnswer> userPaperAnswer = new EntityWrapper<>();
                userPaperAnswer.eq("user_id",teacherStudent.getStudentId());
                userPaperAnswer.eq("paper_id",paper.getId());
                //如果存在
                if(userPaperAnswerMapper.selectCount(userPaperAnswer)>0){
                    Subject subject = new Subject();
                    subject.setId(paper.getSubjectId());
                    User user = new User();
                    user.setId(teacherStudent.getStudentId());
                    // ExerciseServiceImpl exerciseService = new ExerciseServiceImpl();
                    // Double total = exerciseService.getTotal(paper.getId());
                    Double total = getTotal(paper.getId());
                    PaperDao paperDao = PaperDao.builder()
                            .studentId(teacherStudent.getStudentId())
                            .id(paper.getId())
                            .name(paper.getName())
                            .subject(subjectMapper.selectOne(subject).getName())
                            .student(userMapper.selectOne(user).getName())
                            .total(total)
                            .build();
                    UserPaperScore userPaperScore = new UserPaperScore();
                    userPaperScore.setUserId(teacherStudent.getStudentId());
                    userPaperScore.setPaperId(paper.getId());
                    UserPaperScore nowUserPaperScore = userPaperScoreMapper.selectOne(userPaperScore);
                    if (nowUserPaperScore == null){
                        paperDao.setType(ROLE_TEACHER);
                    }else {
                        paperDao.setType(ROLE_STUDENT);
                        paperDao.setScore(nowUserPaperScore.getScore());
                    }
                    paperDaoList.add(paperDao);
                }
            }

        }

        return paperDaoList;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void testPapers(Long paperId, Long studentId, String scores) {
        UserPaperScore userPaperScore = new UserPaperScore();
        userPaperScore.setPaperId(paperId);
        userPaperScore.setUserId(studentId);
        try{
            int score = 0;
            List<ScoreDao> scoreDaoList = mapper.readValue(scores,new TypeReference<List<ScoreDao>>(){
            });
            for (ScoreDao scoreDao:scoreDaoList) {
                int exeScore = Integer.parseInt(scoreDao.getScore());
                score += exeScore;
                UserPaperAnswer userPaperAnswer = UserPaperAnswer.builder()
                        .userId(studentId)
                        .exerciseId(Long.parseLong(scoreDao.getId()))
                        .build();
                UserPaperAnswer nowUserPaperAnswer = userPaperAnswerMapper.selectOne(userPaperAnswer);
                if (nowUserPaperAnswer != null){
                    nowUserPaperAnswer.setScore(exeScore);
                    userPaperAnswerMapper.insert(nowUserPaperAnswer);
                }
            }
            userPaperScore.setScore(score);
            userPaperScoreMapper.insert(userPaperScore);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public boolean addExerciseCollections(Long userId, Long exerciseId) {
//        User user = userMapper.selectById(userId);
//        Exercise exercise = exerciseMapper.selectById(exerciseId);
//        user.getExerciseCollection().add(exercise);
//        return  userMapper.insert(user) >0 ;
//    }

//    @Override
//    public Set<Exercise> getExerciseConllection(Long userId) {
//        Set<Exercise> exerciseSet = userMapper.selectById(userId).getExerciseCollection();
//        for (Exercise exercise:exerciseSet) {
//            if (QuestionType.TYPE_SINGLE_CHOOSE.equals(exercise.getType()) || QuestionType.TYPE_MULTI_CHOOSE.equals(exercise.getType())){
//                Map<Character,String> characterStringMap = JsonUtils.instance.readJsonToExeMap(exercise.getChooses());
//                exercise.setChooseList(characterStringMap);
//            }
//        }
//        return exerciseSet;
//    }

    @Override
    public List<PaperDao> getPapers(Long studentId) {
        List<Paper> paperList = new ArrayList<>();
        EntityWrapper<TeacherStudent> teacherStudentEntityWrapper = new EntityWrapper<>();
        teacherStudentEntityWrapper.eq("student_id",studentId);
        List<TeacherStudent> teacherStudentList = teacherStudentMapper.selectList(teacherStudentEntityWrapper);
        for (TeacherStudent teacherStudent:teacherStudentList) {
            EntityWrapper<Paper> paperEntityWrapper = new EntityWrapper<>();
            paperEntityWrapper.eq("owner_id",teacherStudent.getTeacherId());
            paperList.addAll(paperMapper.selectList(paperEntityWrapper));
        }

        List<PaperDao> paperDaoList = new ArrayList<>();
        for (Paper paper:paperList) {

            PaperDao paperDao = PaperDao.builder()
                    .id(paper.getId())
                    .name(paper.getName())
                    .teacher(userMapper.selectById(paper.getOwnerId()).getName())
                    .subject(subjectMapper.selectById(paper.getSubjectId()).getName())
                    .build();

            UserPaperScore userPaperScore = new UserPaperScore();
            userPaperScore.setUserId(studentId);
            userPaperScore.setPaperId(paper.getId());
            UserPaperScore nowUserPaperScore = userPaperScoreMapper.selectOne(userPaperScore);
            //判断userPaperAnswer是否存在
            EntityWrapper<UserPaperAnswer> userPaperAnswerEntityWrapper = new EntityWrapper<>();
            userPaperAnswerEntityWrapper.eq("user_id",studentId);
            userPaperAnswerEntityWrapper.eq("paper_id",paper.getId());
            if (nowUserPaperScore != null){
                paperDao.setType(3);
                paperDao.setScore(nowUserPaperScore.getScore());
            }else if (userPaperAnswerMapper.selectCount(userPaperAnswerEntityWrapper)>0){
                paperDao.setType(2);
            }else {
                paperDao.setType(1);
            }
            paperDaoList.add(paperDao);
        }
        return paperDaoList;
    }

    @Override
    public void answerPaper(Long studentId, Long paperId, String answer) {
        try{
            List<AnswerDao> answerDaoList = mapper.readValue(answer,new TypeReference<List<AnswerDao>>(){});
            List<UserPaperAnswer> userPaperAnswerList = new ArrayList<>();
            for (AnswerDao answerDao:answerDaoList) {
                UserPaperAnswer userPaperAnswer = UserPaperAnswer.builder()
                        .paperId(paperId)
                        .userId(studentId)
                        .exerciseId(Long.parseLong(answerDao.getId()))
                        .answer(answerDao.getValue())
                        .build();
                userPaperAnswerList.add(userPaperAnswer);
            }

            if (userPaperAnswerList != null) {
                for (UserPaperAnswer userPaperAnswer : userPaperAnswerList) {
                    userPaperAnswerMapper.insert(userPaperAnswer);
                }
            }


        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Double getTotal(Long paperId) {

        Double total = 0d;
        EntityWrapper<Exercise> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("paper_id", paperId);
        List<Exercise> exerciseList = exerciseMapper.selectList(entityWrapper);
        if (exerciseList != null) {
            for (Exercise e : exerciseList) {
                total += e.getScore();
            }
        }
        return total;
    }
}
