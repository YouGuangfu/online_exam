package com.you.online_exam.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.you.online_exam.config.QuestionType;
import com.you.online_exam.dao.ExerciseDao;
import com.you.online_exam.dao.ExerciseFront;
import com.you.online_exam.dao.UserDao;
import com.you.online_exam.entity.Exercise;
import com.you.online_exam.entity.UserPaperAnswer;
import com.you.online_exam.exception.CommonException;
import com.you.online_exam.mapper.ExerciseMapper;
import com.you.online_exam.mapper.UserPaperAnswerMapper;
import com.you.online_exam.service.ExerciseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.you.online_exam.utils.JsonUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 尤广富
 * @since 2019-03-26
 */
@Service
public class ExerciseServiceImpl extends ServiceImpl<ExerciseMapper, Exercise> implements ExerciseService {

    @Autowired
    ExerciseMapper exerciseMapper;
    @Autowired
    UserPaperAnswerMapper userPaperAnswerMapper;
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public Exercise getExercise(Long exerciseId) {
        if(exerciseId != null){
            return exerciseMapper.selectById(exerciseId);
        }
        return null;
    }

    @Override
    public List<Exercise> getAllExercisesByPaperId(Long paperId) {
        EntityWrapper<Exercise> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("paperId",paperId);
        entityWrapper.orderBy("position");
        List<Exercise> exerciseList = exerciseMapper.selectList(entityWrapper);
        for (Exercise exercise:exerciseList) {
            if(QuestionType.TYPE_SINGLE_CHOOSE.equals(exercise.getType()) || QuestionType.TYPE_MULTI_CHOOSE.equals(exercise.getType())){
                Map<Character,String> characterStringMap = JsonUtils.instance.readJsonToExeMap(exercise.getChooses());
                exercise.setChooseList(characterStringMap);
            }
        }
        return exerciseList;
    }

    @Override
    public boolean addExercise(ExerciseDao exerciseDao) {
        String type = exerciseDao.getType();
        int position = getPosition(exerciseDao.getPaperId());
        Exercise exercise = new Exercise();
        BeanUtils.copyProperties(exerciseDao,exercise);
        exercise.setPosition(position);
        if (QuestionType.TYPE_SINGLE_CHOOSE.equals(type) || QuestionType.TYPE_MULTI_CHOOSE.equals(type)){
            //保证两个选项
            if (StringUtils.isEmpty(exerciseDao.getA()) || StringUtils.isEmpty(exerciseDao.getB())){
                throw new CommonException("创建错误");
            }
            Map<Character,String> chooseMap = new HashMap<>(4);
            if (!StringUtils.isEmpty(exerciseDao.getA())){
                chooseMap.put('A',exerciseDao.getA());
            }
            if (!StringUtils.isEmpty(exerciseDao.getB())){
                chooseMap.put('B',exerciseDao.getB());
            }
            if (!StringUtils.isEmpty(exerciseDao.getC())){
                chooseMap.put('C',exerciseDao.getC());
            }
            if (!StringUtils.isEmpty(exerciseDao.getD())){
                chooseMap.put('D',exerciseDao.getD());
            }
            try{
                String chooseJson = mapper.writeValueAsString(chooseMap);
                exercise.setChooses(chooseJson);
            }catch (JsonProcessingException e){
                throw new CommonException("json转换错误");
            }
        }
        return exerciseMapper.insert(exercise)>0;
    }

    @Override
    public List<Exercise> getExercises(Long paperId, Long studentId) {
        EntityWrapper<Exercise> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("paperId",paperId);
        entityWrapper.orderBy("position");
        List<Exercise> exerciseList = exerciseMapper.selectList(entityWrapper);
        for (Exercise exercise:exerciseList) {
            exercise.setScore(exercise.getScore());
            UserPaperAnswer userPaperAnswer = new UserPaperAnswer();
            userPaperAnswer.setUserId(studentId);
            userPaperAnswer.setExerciseId(exercise.getId());
            UserPaperAnswer nowUserPaperAnswer = userPaperAnswerMapper.selectOne(userPaperAnswer);

            exercise.setStudentAnswer(nowUserPaperAnswer.getAnswer());
            if (QuestionType.TYPE_SINGLE_CHOOSE.equals(exercise.getType()) || QuestionType.TYPE_MULTI_CHOOSE.equals(exercise.getType())){
                Map<Character,String> characterStringMap = JsonUtils.instance.readJsonToExeMap(exercise.getChooses());
                exercise.setChooseList(characterStringMap);
            }
        }
        return exerciseList;
    }

    @Override
    public ExerciseFront getAllByType(String type, Pageable pageable) {

        Page<Exercise> exercisePage;
        Long count;
        if (type.equals("all")){
//            exercisePage = exerciseMapper.selectPage(pageable,null);
        }

        return null;
    }

    private int getPosition(Long paperId){
        EntityWrapper<Exercise> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("paperId",paperId);
        entityWrapper.orderBy("position");
        List<Exercise> exerciseList = exerciseMapper.selectList(entityWrapper);
        if (exerciseList.isEmpty()){
            return 1;
        }
        return exerciseList.get(exerciseList.size()-1).getPosition() +1;
    }
}
