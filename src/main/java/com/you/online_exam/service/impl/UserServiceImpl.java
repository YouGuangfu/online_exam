package com.you.online_exam.service.impl;

import com.you.online_exam.entity.User;
import com.you.online_exam.mapper.UserMapper;
import com.you.online_exam.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
