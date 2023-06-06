package com.wan.hospital.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author 心缘星雨
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null) {
            request.setAttribute("msg","请先登录");
            request.getRequestDispatcher("/login").forward(request,response);
            return false;
        } else {
            request.setAttribute("loginUser",loginUser);
            return true;
        }
    }
}
