package com.you.online_exam.service;

import com.you.online_exam.dao.PaperDao;
import com.you.online_exam.entity.TeacherStudent;
import com.baomidou.mybatisplus.service.IService;
import com.you.online_exam.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 尤广富
 * @since 2019-03-26
 */
public interface TeacherStudentService extends IService<TeacherStudent> {

    /**
     * 获取所有学生
     * @return
     */
    List<User> getAllStudents();

    /**
     * 根据教师id获取学生
     * @param teacherId
     * @return
     */
    List<User> getAllStudentsByTeacherId(Long teacherId);

    /**
     * 添加学生
     * @param teacherId
     * @param studentId
     * @return
     */
    boolean addStudent(Long teacherId,Long studentId);

    /**
     * 根据教师id获取试卷
     * @param teacherId
     * @return
     */
    List<PaperDao> getPapersByTeacherId(Long teacherId);

    /**
     * 批改试卷
     * @param paperId
     * @param studentId
     * @param scores
     */
    void testPapers(Long paperId,Long studentId,String scores);

}
