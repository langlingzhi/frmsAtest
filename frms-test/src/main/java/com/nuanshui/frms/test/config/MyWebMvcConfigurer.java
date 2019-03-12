package com.nuanshui.frms.test.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class MyWebMvcConfigurer extends WebMvcConfigurerAdapter {
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(new String[]{"/**"}).excludePathPatterns(new String[]{"/tologin", "/logout", "/login", "/frmsEnv/getQryFrmsEnv", "/frmsApi/getQryFrmsApi", "/frmsCase/toFrmsCaseLeft", "/frmsTask/getQryFrmsTask", "/frmsReport/getQryFrmsReport", "/frmsReport/toTable"});


        super.addInterceptors(registry);
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/complaintsMng/complaintsCount").setViewName("/complaintsMng/complaintsInfos");
    }

}
