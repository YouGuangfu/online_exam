package com.you.online_exam.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.you.online_exam.entity.Exercise;
import com.you.online_exam.entity.Paper;
import com.you.online_exam.entity.Subject;
import com.you.online_exam.exception.CommonException;
import com.you.online_exam.mapper.ExerciseMapper;
import com.you.online_exam.mapper.PaperMapper;
import com.you.online_exam.mapper.SubjectMapper;
import com.you.online_exam.service.PaperService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Action;
import java.util.ArrayList;
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
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements PaperService {

    @Autowired
    ExerciseMapper exerciseMapper;

    @Autowired
    PaperMapper paperMapper;

    @Autowired
    SubjectMapper subjectMapper;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean addPaper(String title, Long subjectId, int answerTime, Long teacherId) {
        Paper paper = Paper.builder()
                .name(title)
                .subjectId(subjectId)
                .ownerId(teacherId)
                .answerTime(answerTime)
                .build();
        return paperMapper.insert(paper)>0;
    }

    @Override
    public boolean delPaper(Long paperId, Long teacherId) {
        Paper paper = paperMapper.selectById(paperId);
        if (paper == null){
            return false;
        }
        if (!paper.getOwnerId().equals(teacherId)){
            throw new CommonException("参数错误");
        }
        return paperMapper.deleteById(paperId)>0;
    }

    @Override
    public List<Paper> getPapersByTeacherId(Long teacherId) {
        EntityWrapper<Paper> paperEntityWrapper = new EntityWrapper<>();
        paperEntityWrapper.eq("owner_id",teacherId);
        List<Paper> paperList = paperMapper.selectList(paperEntityWrapper);
        for (Paper paper:paperList) {
            Subject subject = new Subject();
            subject.setId(paper.getSubjectId());
            String name = subjectMapper.selectOne(subject).getName();
            paper.setSubject(name);
        }
        return paperList;
    }

    @Override
    public Paper getPaperById(Long id) {
        Paper paper = paperMapper.selectById(id);
        if (paper == null){
            throw new CommonException("试卷不存在");
        }
        return paper;
    }

    @Override
    public List<Paper> getAllPapers() {
        List<Paper> paperList = paperMapper.selectList(null);
        for (Paper paper:paperList) {
            Subject subject = new Subject();
            subject.setId(paper.getSubjectId());
            String nowSubject = subjectMapper.selectOne(subject).getName();
            paper.setSubject(nowSubject);
        }
        return paperList;
    }

    @Override
    public void test() {
//        Exercise exercise = new Exercise();
    }
}
