package com.poscodx.mysite.web.utils;

import javax.servlet.http.HttpServletRequest;

public class WebUtil  {
    public static void forward(String path, HttpServletRequest request) {
        request
                .getRequestDispatcher("/WEB-INF/views/"+ path + ".jsp")
                .forward(request, response);
    }
}
