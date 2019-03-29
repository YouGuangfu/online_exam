package com.you.online_exam.service;

import com.you.online_exam.entity.Subject;
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
public interface SubjectService extends IService<Subject> {

    /**
     * 获取所有科目
     * @return
     */
    List<Subject> getAllSubjects();

    /**
     * 添加科目
     * @param name
     * @return
     */
    boolean addSubject(String name);
}
