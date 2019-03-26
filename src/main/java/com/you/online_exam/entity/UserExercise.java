package com.you.online_exam.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("user_exercise")
public class UserExercise implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("User_id")
    private Long userId;
    @TableField("exerciseCollection_id")
    private Long exercisecollectionId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getExercisecollectionId() {
        return exercisecollectionId;
    }

    public void setExercisecollectionId(Long exercisecollectionId) {
        this.exercisecollectionId = exercisecollectionId;
    }

    @Override
    public String toString() {
        return "UserExercise{" +
        ", userId=" + userId +
        ", exercisecollectionId=" + exercisecollectionId +
        "}";
    }
}
