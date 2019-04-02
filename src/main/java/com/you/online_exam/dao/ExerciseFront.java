package com.you.online_exam.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.you.online_exam.entity.Exercise;

import java.util.List;

/**
 * @author 尤广富 (Online Class)
 * @create 2019-04-01 16:39
 **/
public class ExerciseFront {

    public final List<Exercise> exercisePage;
    public final Long count;


    public ExerciseFront(List<Exercise> exercisePage, Long count) {
        this.exercisePage = exercisePage;
        this.count = count;
    }
}
