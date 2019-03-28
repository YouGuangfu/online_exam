package com.you.online_exam.dao;

import lombok.Builder;
import lombok.Data;

/**
 * 试卷dao
 *
 * @author 尤广富 (Online Class)
 * @create 2019-03-28 15:32
 **/
@Data
@Builder
public class PaperDao {

    private Long id;

    private String name;

    private String teacher;

    private String student;

    private Long studentId;

    private String subject;

    private int type;

    private double score;
}
