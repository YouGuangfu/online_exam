package com.you.online_exam.service;

import com.you.online_exam.entity.Exercise;
import com.baomidou.mybatisplus.service.IService;

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


}
