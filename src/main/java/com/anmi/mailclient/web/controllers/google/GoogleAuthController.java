package com.anmi.mailclient.web.controllers.google;

import com.anmi.mailclient.core.configuration.ConfigurationProperties;
import com.anmi.mailclient.core.configuration.ConfigurationService;
import com.anmi.mailclient.core.security.oauth.OAuth2GoogleService;
import com.anmi.mailclient.core.security.oauth.GoogleContextProvider;
import com.anmi.mailclient.web.dto.google.oauth.GoogleAuthTokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.MessageFormat;

@Controller
public class GoogleAuthController extends BaseGoogleController {


    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private OAuth2GoogleService oAuth2GoogleService;

    @Autowired
    private GoogleContextProvider googleContextProvider;

    @RequestMapping(method = RequestMethod.GET, value = "/auth")
    @ResponseStatus(HttpStatus.OK)
    public void getAuthCode(HttpServletResponse response) throws IOException {
        String code_url = configurationService.getProperty(ConfigurationProperties.OAUTH_CODE_URL);
        String clientId = configurationService.getProperty(ConfigurationProperties.OAUTH_CLIENT_ID);
        String site_url = configurationService.getProperty(ConfigurationProperties.SITE_URL);
        String scope = googleContextProvider.getScope();
        response.sendRedirect(MessageFormat.format(code_url, scope, URLEncoder.encode(site_url, "UTF-8"), clientId));
    }


    @RequestMapping(method = RequestMethod.GET, value = "/redirect")
    @ResponseStatus(HttpStatus.OK)
    public void redirectAndGetAuthToken(@RequestParam("code") String code, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws URISyntaxException, IOException, ServletException {
        GoogleAuthTokenDto googleAuthTokenDto = (GoogleAuthTokenDto) oAuth2GoogleService.getAuthToken(code);
        googleContextProvider.setToken(googleAuthTokenDto);
        httpServletResponse.sendRedirect(googleContextProvider.getPath());

    }


}
