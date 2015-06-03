package com.anmi.mailclient.web.interceptor;

import com.anmi.mailclient.web.dto.google.oauth.GoogleAuthTokenDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoogleResourceInterceptor extends HandlerInterceptorAdapter {
    public static  String path = null;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        GoogleAuthTokenDto googleAuthTokenDto = getToken(request);
        String authUrl = "/g/auth";
        String code = (String) request.getParameter("code");


        if(googleAuthTokenDto == null && (!request.getServletPath().equals(authUrl) && !request.getServletPath().equals("/g/redirect"))){
            path = request.getContextPath()+request.getServletPath();

        }

        if (googleAuthTokenDto != null) {
           return true;
        }

        if (request.getServletPath().equals(authUrl)) {
            return true;
        }

        if (request.getServletPath().equals("/g/redirect") && !StringUtils.isBlank(code)) {
            return true;
        }

        if (googleAuthTokenDto == null && !request.getServletPath().equals(authUrl)) {
            response.sendRedirect("/mail-app/g/auth");
            return false;
        }





        return false;

    }

    private GoogleAuthTokenDto getToken(HttpServletRequest request) {
        return (GoogleAuthTokenDto) request.getSession().getAttribute("access_token");
    }


}
