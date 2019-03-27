package com.you.online_exam.service.impl;

import com.you.online_exam.entity.Subject;
import com.you.online_exam.entity.User;
import com.you.online_exam.mapper.SubjectMapper;
import com.you.online_exam.mapper.UserMapper;
import com.you.online_exam.service.InitService;
import com.you.online_exam.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 初始化默认service实现
 *
 * @author 尤广富 (Online Class)
 * @create 2019-03-27 17:48
 **/
public class InitServiceImpl implements InitService {

    private static final int ROLE_STUDENT = 1;
    private static final int ROLE_TEACHER = 2;

    @Autowired
    UserMapper userMapper;
    @Autowired
    SubjectMapper subjectMapper;

    @PostConstruct
    @Transactional
    @Override
    public void initExecute() {
        userInit();
        subjectInit();
    }

    /**
     * 初始化默认userdate
     */
    private void userInit(){
        //学生
        User stu = userMapper.selectOne(new User("you"));
        if(stu == null){
            String pwd = EncryptUtils.EncoderByMd5("you");
            //重新生成一个user
            userMapper.insert(new User("you",pwd,ROLE_STUDENT));
        }
        //教师
        User teacher = userMapper.selectOne(new User("tea"));
        if(teacher == null){
            String pwd = EncryptUtils.EncoderByMd5("tea");
            //重新生成一个user
            userMapper.insert(new User("tea",pwd,ROLE_TEACHER));
        }
    }

    /**
     * 课程初始化
     */
    private void subjectInit(){
        long count = subjectMapper.selectCount(null);
        if (count <1){
            subjectMapper.insert(new Subject("语文"));
            subjectMapper.insert(new Subject("数学"));
            subjectMapper.insert(new Subject("英语"));
        }
    }
}
