package com.you.online_exam.service;

import com.you.online_exam.dao.ExerciseDao;
import com.you.online_exam.dao.ExerciseFront;
import com.you.online_exam.entity.Exercise;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 尤广富
 * @since 2019-03-26
 */
public interface ExerciseService extends IService<Exercise> {

    /**
     * 获取习题信息
     * @param exerciseId
     * @return
     */
    Exercise getExercise(Long exerciseId);

    /**
     * 根据试卷id获取所有练习
     * @param paperId
     * @return
     */
    List<Exercise> getAllExercisesByPaperId(Long paperId);

    /**
     * 添加习题
     * @param exerciseDao
     */
    boolean addExercise(ExerciseDao exerciseDao);

    /**
     * 根据paperId和studentId查找测试题
     * @param paperId
     * @param studentId
     * @return
     */
    List<Exercise> getExercises(Long paperId,Long studentId);

    /**
     * 分页查找试题信息
     * @param type
     * @param pageable
     * @return
     */
    ExerciseFront getAllByType(String type, Pageable pageable);

}
