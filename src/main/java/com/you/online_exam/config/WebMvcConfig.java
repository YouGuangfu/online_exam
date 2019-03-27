package com.you.online_exam.config;

import com.you.online_exam.interceptor.UserSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * webmvc配置
 *
 * @author 尤广富 (Online Class)
 * @create 2019-03-27 10:38
 **/
public class WebMvcConfig extends WebMvcConfigurationSupport {

    private UserSecurityInterceptor userSecurityInterceptor;

    @Autowired
    public WebMvcConfig(UserSecurityInterceptor userSecurityInterceptor){ this.userSecurityInterceptor = userSecurityInterceptor;}

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userSecurityInterceptor).addPathPatterns("/user/**");
    }
}
