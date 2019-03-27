package com.you.online_exam.service.impl;

import com.you.online_exam.dao.UserDao;
import com.you.online_exam.entity.User;
import com.you.online_exam.exception.CommonException;
import com.you.online_exam.mapper.UserMapper;
import com.you.online_exam.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.you.online_exam.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 尤广富
 * @since 2019-03-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static final String DEFAULT_AVATAR="/img/default.png";


    @Autowired
    UserMapper userMapper;
    @Override
    public boolean login(User user, HttpServletRequest httpServletRequest) {

        //先判断参数是否为空
        if(CommonUtils.isEmpty(user.getName(),user.getPassword())){}

        User nowUser = userMapper.selectOne(user);
        if (nowUser != null){
            user.setPassword(null);
            //创建一个会话session
            HttpSession session = httpServletRequest.getSession(true);
            //设置user
            UserDao userDao = CommonUtils.SetUserDao(nowUser);
            //设置当前用户
            session.setAttribute("cur_user",userDao);
            //设置session失效时间
            session.setMaxInactiveInterval(600);
            return true;
        }
        return false;
    }

    @Override
    public void userRegister(User user) {
        if (user == null){
            return;
        }
        user.setAvatarUrl(DEFAULT_AVATAR);
        userMapper.insert(user);
    }

    @Override
    public boolean userExist(User user) {
        if (user == null){
            throw new CommonException("未输入用户");
        }
        return userMapper.selectOne(user) != null;
    }
}
