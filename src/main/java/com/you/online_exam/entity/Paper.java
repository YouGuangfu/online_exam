package com.you.online_exam.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 尤广富
 * @since 2019-03-26
 */
public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("answer_time")
    private Integer answerTime;
    private String name;
    @TableField("owner_id")
    private Long ownerId;
    @TableField("subject_id")
    private Long subjectId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Integer answerTime) {
        this.answerTime = answerTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "Paper{" +
        ", id=" + id +
        ", answerTime=" + answerTime +
        ", name=" + name +
        ", ownerId=" + ownerId +
        ", subjectId=" + subjectId +
        "}";
    }
}
