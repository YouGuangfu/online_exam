package com.you.online_exam.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.you.online_exam.entity.Subject;
import com.you.online_exam.exception.CommonException;
import com.you.online_exam.mapper.SubjectMapper;
import com.you.online_exam.service.SubjectService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 尤广富
 * @since 2019-03-26
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    SubjectMapper subjectMapper;
    @Override
    public List<Subject> getAllSubjects() {
        return  subjectMapper.selectList(null);
    }

    @Override
    public boolean addSubject(String name) {
        //先查看是否存在该subject
        EntityWrapper<Subject> subjectEntityWrapper = new EntityWrapper<>();
        subjectEntityWrapper.eq("name",name);
        int isExist = subjectMapper.selectCount(subjectEntityWrapper);
        if (isExist >0){
            return false;
        }
        Subject subject = new Subject();
        subject.setName(name);
        return subjectMapper.insert(subject)>0;
    }
}
