package com.you.online_exam.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("user_paper_answer")
@Builder
public class UserPaperAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String answer;
    @TableField("exercise_id")
    private Long exerciseId;
    @TableField("paper_id")
    private Long paperId;
    private Integer score;
    @TableField("user_id")
    private Long userId;


}
