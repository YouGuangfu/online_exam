package com.you.online_exam.dao;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;

/**
 * @author 尤广富 (Online Class)
 * @create 2019-03-29 11:40
 **/
@Data
public class ExerciseDao {
    @NotEmpty
    private String title;
    private String content;
    private long subjectId;
    @NotEmpty
    private String type;
    @Min(1)
    private int score;
    private String A;
    private String B;
    private String C;
    private String D;
    private String remark;
    private long paperId;

    public ExerciseDao() {
    }
}
