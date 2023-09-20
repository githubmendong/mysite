package com.poscodx.mysite.security;

import com.poscodx.mysite.vo.UserVo;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Authinterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1. hander 종류 확인
        if (!(handler instanceof HandlerMethod)){
            // DefaultServletHanlder가 처리하는 경우 (정적 자원, /assets/**)
            return true;
        }

        //2.casting
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        
        // 3. Handler Method의 @Auth 가져오기
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
        
        // 4. @Auth가 없는 경우
        if (auth == null){
            return true;
        }

        // 5. @Auth가 붙어 있는 경우, 인증 (Authenfication) 여부 확인
        HttpSession session = request.getSession();
        UserVo authUser = (UserVo)
                session.getAttribute("authUser");

        if (authUser == null){
            response.sendRedirect(request.getContextPath()+"/user/login");
        }

        // 6. 인증 확인
        return true;
    }
}