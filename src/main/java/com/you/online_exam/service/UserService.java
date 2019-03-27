package com.you.online_exam.service;

import com.you.online_exam.entity.User;
import com.baomidou.mybatisplus.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 尤广富
 * @since 2019-03-26
 */
public interface UserService extends IService<User> {

    /**
     * 登录功能
     * @param user
     * @param httpServletRequest
     * @return
     */
    boolean login(User user, HttpServletRequest httpServletRequest);

    /**
     * 注册功能
     * @param user
     */
    void userRegister(User user);

    /**
     * 判断该用户是否存在
     * @param user
     * @return
     */
    boolean userExist(User user);



}
