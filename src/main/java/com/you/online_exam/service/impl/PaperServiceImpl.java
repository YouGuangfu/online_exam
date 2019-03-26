package com.you.online_exam.service.impl;

import com.you.online_exam.entity.Exercise;
import com.you.online_exam.entity.Paper;
import com.you.online_exam.mapper.ExerciseMapper;
import com.you.online_exam.mapper.PaperMapper;
import com.you.online_exam.service.PaperService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 尤广富
 * @since 2019-03-26
 */
@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements PaperService {

    @Autowired
    ExerciseMapper exerciseMapper;



    @Override
    public void test() {
        Exercise exercise = new Exercise();
    }
}
