package com.you.online_exam.service.impl;

import com.you.online_exam.entity.Subject;
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
}
