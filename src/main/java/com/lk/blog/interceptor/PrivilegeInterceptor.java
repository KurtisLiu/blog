package com.lk.blog.interceptor;

import com.lk.blog.annotation.Login;
import com.lk.blog.constans.ApplicationConstants;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

public class PrivilegeInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            Login annotation = ((HandlerMethod)handler).getMethodAnnotation(Login.class);
            if (annotation != null) {
                HttpSession session = httpServletRequest.getSession();
                if (session.getAttribute(ApplicationConstants.LOGIN_USER) == null) {
                    String returnUrl = URLEncoder.encode(httpServletRequest.getRequestURL().toString(), "UTF-8");
                    httpServletResponse.sendRedirect("/user/login?returnUrl=" + returnUrl);
                    return false;
                }
            }
        }
        return true;
    }
}
