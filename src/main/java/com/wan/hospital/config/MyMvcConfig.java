package com.wan.hospital.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 心缘星雨
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logon.html").setViewName("logon");
        registry.addViewController("/patient").setViewName("patient");
        registry.addViewController("/patient.html").setViewName("patient");
        registry.addViewController("/doctor").setViewName("doctor");
        registry.addViewController("/logonD.html").setViewName("logonD");
        registry.addViewController("/welcome").setViewName("welcome");
        registry.addViewController("/selectCondition").setViewName("selectCondition");
        registry.addViewController("/doctorlist").setViewName("doctorlist");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/","/user/login","/css/**","/js/**","/img/**","/logon.html","/logonD.html");
    }
}
