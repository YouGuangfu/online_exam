package com.you.online_exam.utils;

import com.you.online_exam.dao.UserDao;
import com.you.online_exam.entity.User;

import java.util.regex.Pattern;

/**
 * 工具类
 *
 * @author 尤广富 (Online Class)
 * @create 2019-03-27 17:18
 **/
public class CommonUtils {

    private static final String NUMBER = "[0-9]*";

    public static boolean isEmpty(String...strings){
        for (String string:strings){
            if (string==null || string.trim().isEmpty()){
                return true;
            }
        }
        return false;
    }

    public static boolean notEmpty(String...strings){
        for (String string:strings){
            if (string==null || string.trim().isEmpty()){
                return false;
            }
        }
        return true;
    }

    public static UserDao SetUserDao(User user){

        return new UserDao(
                user.getId(),
                user.getName(),
                user.getRole(),
                user.getAvatarUrl()
        );

    }

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile(NUMBER);
        return pattern.matcher(str).matches();
    }
}
