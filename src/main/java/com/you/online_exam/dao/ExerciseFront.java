package com.you.online_exam.dao;

import com.you.online_exam.entity.Exercise;
import org.springframework.data.domain.Page;

/**
 * @author 尤广富 (Online Class)
 * @create 2019-04-01 16:39
 **/
public class ExerciseFront {

    public final Page<Exercise> exercisePage;
    public final Long count;


    public ExerciseFront(Page<Exercise> exercisePage, Long count) {
        this.exercisePage = exercisePage;
        this.count = count;
    }
}
