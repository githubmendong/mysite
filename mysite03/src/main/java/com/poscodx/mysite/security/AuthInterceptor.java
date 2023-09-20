package com.poscodx.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import com.poscodx.mysite.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // Handler의 종류가 HandlerMethod가 아닐 경우(예: 정적 자원 처리), Interceptor는 그대로 통과시킨다.
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }

        // Handler를 HandlerMethod로 캐스팅한다.
        HandlerMethod handlerMethod = (HandlerMethod)handler;

        // Method level의 @Auth 어노테이션이 있는지 확인한다.
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

        // Method level에 @Auth 어노테이션이 없다면, Class level의 @Auth 어노테이션을 확인한다.
        if(auth == null) {
            auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
        }

        // @Auth 어노테이션이 없으면 인증 체크 없이 요청을 진행시킨다.
        if(auth == null) {
            return true;
        }


        HttpSession session = request.getSession();
        UserVo authUser = (UserVo)session.getAttribute("authUser");

        // 세션에 'authUser'라는 이름으로 저장된 사용자 정보가 없으면 로그인 페이지로 리다이렉트하고 요청을 중단한다.
        if(authUser == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }

        String role = auth.Role();

        // "USER" 권한으로 접근 가능하므로 요청을 진행시킨다.
        if("USER".equals(role)) {
            return true;
        }


        // "ADMIN" 권한으로만 접근 가능하므로 'authUser'의 Role 값도 "ADMIN" 이어야 한다.
        // 그렇지 않으면 홈(콘텍스트 경로)으로 리다이렉트하고 요청을 중단한다.
        if(!"ADMIN".equals(authUser.getRole())) {
            response.sendRedirect(request.getContextPath());
            return false;
        }

        // 위의 모든 체크를 통과하면 요청을 계속 진행시킨다.
        return true;
    }
}
