package com.you.online_exam.utils;

import com.you.online_exam.dao.UserDao;
import com.you.online_exam.entity.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 * RequestUtils 请求体工具类
 *
 * @author 尤广富 (Online Class)
 * @create 2019-03-27 14:24
 **/
public class RequestUtils {

    private static final int ROLE_ADMIN = 0;
    private static final int ROLE_STUDENT = 1;
    private static final int ROLE_TEACHER = 2;

    public static UserDao getUserAuxiliaryFromReq(HttpServletRequest request){
        Object obj = request.getSession().getAttribute("cur_user");
        if (obj == null || !(obj instanceof UserDao)) {
            return null;
        }
        return (UserDao) obj;
    }

    public static long getUserIdFromReq(HttpServletRequest request){
        Object obj = request.getSession().getAttribute("cur_user");
        if (obj == null || !(obj instanceof UserDao)) {
            return -1;
        }
        return ((UserDao) obj).getId();
    }

    public static User getUserFromReq(HttpServletRequest request){
        Object obj = request.getSession().getAttribute("cur_user");
        if (obj == null || !(obj instanceof UserDao)) {
            return null;
        }
        UserDao userDao=(UserDao)obj;
        return new User(userDao.getId(),userDao.getName());
    }

    public static void setFrontUserInfo(Model model, HttpServletRequest request){
        UserDao userDao= RequestUtils.getUserAuxiliaryFromReq(request);
        if (userDao==null){
            model.addAttribute("type",ROLE_ADMIN);
        }else if (userDao.getType()==1){
            model.addAttribute("type",ROLE_STUDENT);
            model.addAttribute("name",userDao.getName());
        }else if (userDao.getType()==2){
            model.addAttribute("type",ROLE_TEACHER);
            model.addAttribute("name",userDao.getName());
        }
    }
}

