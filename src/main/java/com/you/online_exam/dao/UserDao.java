package com.you.online_exam.dao;

import lombok.Data;

import java.io.Serializable;

/**
 * User辅助类
 *
 * @author 尤广富 (Online Class)
 * @create 2019-03-27 10:31
 **/
@Data
public class UserDao implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 类型
     * 1：学生，2：老师，3：管理员
     */
    private int type;

    /**
     * 头像
     */
    private String avatarUrl;


    public UserDao(Long id, String name, int type, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.avatarUrl = avatarUrl;
    }
}
