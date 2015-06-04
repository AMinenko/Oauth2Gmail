package com.anmi.mailclient.core.service.impl;

import com.anmi.mailclient.core.configuration.ConfigurationService;
import com.anmi.mailclient.core.rest.Request;
import com.anmi.mailclient.core.rest.RestBasedAPIService;
import com.anmi.mailclient.core.rest.RestResource;
import com.anmi.mailclient.core.security.oauth.OAuth2GoogleService;
import com.anmi.mailclient.core.security.oauth.GoogleContextProvider;

import com.anmi.mailclient.web.dto.google.gmail.GmailDto;
import com.anmi.mailclient.web.dto.google.oauth.GoogleAuthTokenDto;
import org.codehaus.plexus.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.springframework.http.HttpMethod.GET;

@Service
public class GmailServiceImpl extends RestBasedAPIService implements com.anmi.mailclient.core.service.GmailService {
    @Autowired
    private GoogleContextProvider googleContextProvider;

    @Autowired
    private ConfigurationService configurationService;

    private String gmailUser = null;

    @Override
    public List<GmailDto> all() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(OAuth2GoogleService.AUTHORIZATION, "");
        Request.Builder<GmailDto.GmailList> request = new Request.Builder<>(GET, RestResource.GMAIL_GET_MESSAGES.getPath(gmailUser));
        GmailDto.GmailList result = execute(request.responseClass(GmailDto.GmailList.class).headers(headers).build());
        return Arrays.asList(result.getMessages());
    }

    @Override
    public GmailDto get(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(OAuth2GoogleService.AUTHORIZATION, "");
        Request.Builder<GmailDto> request = new Request.Builder<>(GET, RestResource.GMAIL_GET_MESSAGE.getPath(gmailUser,id));
        GmailDto result = execute(request.responseClass(GmailDto.class).headers(headers).build());
        MimeMessage message = getMessage(Base64.decodeBase64(result.getRaw().getBytes(Charset.forName("UTF-8"))));
        return result;
    }

    @Override
    public String getAuthorizationToken() {
        String authToken = "";
        GoogleAuthTokenDto googleAuthTokenDto = (GoogleAuthTokenDto) googleContextProvider.getToken();
        if (googleAuthTokenDto != null) {
            authToken = googleAuthTokenDto.getTokenType() + " " + googleAuthTokenDto.getAccessToken();
        }
        return authToken;
    }

    MimeMessage getMessage(byte[] emailBytes){
        Session session = Session.getDefaultInstance(new Properties());
        MimeMessage message = null;
        try {
             message = new MimeMessage(session, new ByteArrayInputStream(emailBytes));
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }





    @PostConstruct
    public void init(){
        gmailUser = configurationService.getProperty("gmail.user");
    }

}