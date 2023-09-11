package com.poscodx.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EncodingFilter", value = "/EncodingFilter")
public class EncodingFilter extends HttpServlet implements Filter {

    private static final long serivalVersionUID = 1L;
    private String encoding;
    @Override
    public void init(FilterConfig fConfig) throws ServletException{
        encoding = fConfig.getInitParameter("encoding");
        if (encoding == null){
            encoding = "utf-8";
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /* request 처리*/
        //        boolean existJSessionId = false;
        request.setCharacterEncoding(encoding);
        chain.doFilter(request,response);


    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
 
