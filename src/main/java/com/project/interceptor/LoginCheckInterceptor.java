package com.project.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //로그아웃 되었을때 뒤로가기 시 캐시 삭제를 위함(권한있는 페이지로 못가게)
        //response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        //response.setHeader("Pragma", "no-cache");
        //response.setHeader("Expires", "0");

        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();

        if(session == null || session.getAttribute("user") == null){
            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }

        return true;
    }
}
