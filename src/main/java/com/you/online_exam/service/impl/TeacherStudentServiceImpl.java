package com.you.online_exam.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.you.online_exam.dao.PaperDao;
import com.you.online_exam.dao.ScoreDao;
import com.you.online_exam.entity.*;
import com.you.online_exam.mapper.*;
import com.you.online_exam.service.TeacherStudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
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
                    PaperDao paperDao = PaperDao.builder()
                            .studentId(teacherStudent.getStudentId())
                            .id(paper.getId())
                            .name(paper.getName())
                            .subject(subjectMapper.selectOne(subject).getName())
                            .student(userMapper.selectOne(user).getName())
                            .build();
                    UserPaperScore userPaperScore = new UserPaperScore();
                    userPaperScore.setUserId(teacherStudent.getStudentId());
                    userPaperScore.setPaperId(paper.getId());
                    UserPaperScore nowUserPaperScore = userPaperScoreMapper.selectOne(userPaperScore);
                    if (userPaperScore == null){
                        paperDao.setType(ROLE_TEACHER);
                    }else {
                        paperDao.setType(ROLE_STUDENT);
                        paperDao.setScore(userPaperScore.getScore());
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
                UserPaperAnswer userPaperAnswer = new UserPaperAnswer();
                userPaperAnswer.setUserId(studentId);
                userPaperAnswer.setExerciseId(Long.parseLong(scoreDao.getId()));
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
}
