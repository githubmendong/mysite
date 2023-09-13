package com.poscodx.mysite.web.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtil  {
    public static void forward(String path, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request
                .getRequestDispatcher("/WEB-INF/views/" + path + ".jsp")
                .forward(request, response);
    }

}
