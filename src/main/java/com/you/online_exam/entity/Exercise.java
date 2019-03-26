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
public class Exercise implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String chooses;
    private String content;
    private Long paperId;
    private Integer position;
    private String remark;
    private Integer score;
    @TableField("subject_id")
    private Long subjectId;
    private String title;
    private String type;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChooses() {
        return chooses;
    }

    public void setChooses(String chooses) {
        this.chooses = chooses;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Exercise{" +
        ", id=" + id +
        ", chooses=" + chooses +
        ", content=" + content +
        ", paperId=" + paperId +
        ", position=" + position +
        ", remark=" + remark +
        ", score=" + score +
        ", subjectId=" + subjectId +
        ", title=" + title +
        ", type=" + type +
        "}";
    }
}
