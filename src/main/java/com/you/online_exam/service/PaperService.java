package com.you.online_exam.service;

import com.you.online_exam.entity.Paper;
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
public interface PaperService extends IService<Paper> {

    /**
     * 添加试卷
     * @param title
     * @param subjectId
     * @param answerTime
     * @param teacherId
     * @return
     */
    boolean addPaper(String title,Long subjectId,int answerTime,Long teacherId);

    /**
     * 删除试卷
     * @param paperId
     * @param teacherId
     * @return
     */
    boolean delPaper(Long paperId,Long teacherId);

    /**
     * 根据教师id获取试卷
     * @param teacherId
     * @return
     */
    List<Paper> getPapersByTeacherId(Long teacherId);

    /**
     * 获取试卷
     * @param id
     * @return
     */
    Paper getPaperById(Long id);

    /**
     * 获取全部试卷
     * @return
     */
    List<Paper> getAllPapers();

    /**
     * 测试
     */
    void test();

}
