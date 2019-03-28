package com.you.online_exam.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import lombok.Generated;

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
public class User implements Serializable {

    private static final String DEFAULT_AVATAR="/img/default.png";

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("avatar_url")
    private String avatarUrl;
    private String name;
    private String password;
    private Integer role;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(String name) {
        this.name = name;
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name, String password, Integer role) {
        this.avatarUrl = DEFAULT_AVATAR;
        this.name = name;
        this.password = password;
        this.role = role;
    }
}
