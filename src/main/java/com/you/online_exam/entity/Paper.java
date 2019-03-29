package com.you.online_exam.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 尤广富
 * @since 2019-03-26
 */
@Data
@Builder
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

    public Paper(Long id, Integer answerTime, String name, Long ownerId, Long subjectId) {
        this.id = id;
        this.answerTime = answerTime;
        this.name = name;
        this.ownerId = ownerId;
        this.subjectId = subjectId;
    }

    public Paper(Long id, Integer answerTime, String name, Long ownerId, Long subjectId, String subject) {
        this.id = id;
        this.answerTime = answerTime;
        this.name = name;
        this.ownerId = ownerId;
        this.subjectId = subjectId;
        this.subject = subject;
    }

    /**
     * 使表中没有此字段
     */
    @TableField(exist = false)
    private String subject;

}
