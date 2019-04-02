package com.you.online_exam.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 
 * </p>
 *
 * @author 尤广富
 * @since 2019-03-26
 */
@Data
public class Exercise implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 选择题选项列表
     */
    private String chooses;
    /**
     * 习题内容
     */
    private String content;

    @TableField("paper_id")
    private Long paperId;
    /**
     * 习题在试卷中的位置
     */
    private Integer position;
    /**
     * 习题备注信息，比如参考答案等
     */
    private String remark;
    /**
     * 习题分值
     */
    private Integer score;
    @TableField("subject_id")
    private Long subjectId;
    /**
     * 标题
     */
    private String title;
    /**
     * 习题类型
     * 单择题： single_choose
     * 多选题： multi_choose
     * 填空题： completion
     * 简答题： short_answer
     */
    private String type;

    /**
     * 使表中没有此字段
     */
    @TableField(exist = false)
    @JsonIgnore
    private Map<Character, String> chooseList;

    /**
     * 使表中没有此字段
     */
    @TableField(exist = false)
    private String studentAnswer;

    public Exercise() {
    }

    public Exercise(Long id, String chooses, String content, Long paperId, Integer position, String remark, Integer score, Long subjectId, String title, String type) {
        this.id = id;
        this.chooses = chooses;
        this.content = content;
        this.paperId = paperId;
        this.position = position;
        this.remark = remark;
        this.score = score;
        this.subjectId = subjectId;
        this.title = title;
        this.type = type;
    }

    public Exercise(Long id, String chooses, String content, Long paperId, Integer position, String remark, Integer score, Long subjectId, String title, String type, Map<Character, String> chooseList, String studentAnswer) {
        this.id = id;
        this.chooses = chooses;
        this.content = content;
        this.paperId = paperId;
        this.position = position;
        this.remark = remark;
        this.score = score;
        this.subjectId = subjectId;
        this.title = title;
        this.type = type;
        this.chooseList = chooseList;
        this.studentAnswer = studentAnswer;
    }
}
