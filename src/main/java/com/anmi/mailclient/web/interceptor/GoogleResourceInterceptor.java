package com.anmi.mailclient.web.interceptor;

import com.anmi.mailclient.core.configuration.ConfigurationProperties;
import com.anmi.mailclient.core.configuration.ConfigurationService;
import com.anmi.mailclient.core.security.oauth.GoogleContextProvider;
import com.anmi.mailclient.web.dto.BaseTokenDto;
import com.anmi.mailclient.web.dto.google.oauth.GoogleAuthTokenDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class GoogleResourceInterceptor extends HandlerInterceptorAdapter {
    public static String path = null;

    @Autowired
    private GoogleContextProvider googleContextProvider;

    @Autowired
    private ConfigurationService configurationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        GoogleAuthTokenDto googleAuthTokenDto =(GoogleAuthTokenDto) googleContextProvider.getToken();
        String authUrl = "/g/auth";
        String code = (String) request.getParameter("code");


        if (googleAuthTokenDto == null && (!request.getServletPath().equals(authUrl) && !request.getServletPath().equals("/g/redirect"))) {
            path = request.getContextPath() + request.getServletPath();
            googleContextProvider.setPath(path);
            googleContextProvider.setScope(getSope(path.substring(path.lastIndexOf("/") + 1)));
        }

        if (googleAuthTokenDto != null) {
            if (!checkTokenAgainstRequest(googleAuthTokenDto, request)) {
                googleContextProvider.setToken(null);
                path = request.getContextPath() + request.getServletPath();
                googleContextProvider.setPath(path);
                googleContextProvider.setScope(getSope(path.substring(path.lastIndexOf("/") + 1)));
                response.sendRedirect("/mail-app/g/auth");
                return false;
            }
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

    private String getSope(String path) {
        String[] sopes = new String[]{ConfigurationProperties.OAUTH_GMAIL_SCOPE,
                ConfigurationProperties.OAUTH_DRIVE_SCOPE};

        for (String scope : sopes) {
            if (scope.contains(path)) {
                return configurationService.getProperty(scope);
            }
        }
        return "";
    }


    private boolean checkTokenAgainstRequest(GoogleAuthTokenDto googleAuthTokenDto, HttpServletRequest request) {
        String path = request.getServletPath();
        String scope = googleContextProvider.getScope();
        if ((scope.contains("drive") && !path.contains("drive")) ||
                (scope.contains("gmail") && !path.contains("gmail")) ||  scope.isEmpty()) {
            return false;
        }

        return true;

    }


   /* private boolean checkTokenAgainstRequest(GoogleAuthTokenDto googleAuthTokenDto, HttpServletRequest request) {
        String path = request.getServletPath();
        BaseTokenDto.AppType appType = googleAuthTokenDto.getAppType();

        if ((appType.equals(BaseTokenDto.AppType.TYPE_DRIVE) && !path.contains("drive")) ||
                (appType.equals(BaseTokenDto.AppType.TYPE_GMAIL) && !path.contains("gmail"))
                ) {

            return false;
        }
        return true;
    }*/

   /* private GoogleAuthTokenDto getToken(HttpServletRequest request) {
        return (GoogleAuthTokenDto) request.getSession().getAttribute("access_token");
    }
*/

}
