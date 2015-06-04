package com.anmi.mailclient.core.service.impl;
import com.anmi.mailclient.core.configuration.ConfigurationService;
import com.anmi.mailclient.core.rest.Request;
import com.anmi.mailclient.core.rest.RestBasedAPIService;
import com.anmi.mailclient.core.rest.RestResource;
import com.anmi.mailclient.core.security.oauth.OAuth2GoogleService;
import com.anmi.mailclient.core.security.oauth.GoogleContextProvider;
import com.anmi.mailclient.core.service.DriveService;
import com.anmi.mailclient.web.dto.google.drive.DriveDto;
import com.anmi.mailclient.web.dto.google.oauth.GoogleAuthTokenDto;
import com.google.api.client.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Service
public class DriveServiceImpl extends RestBasedAPIService implements DriveService {

    @Autowired
    private GoogleContextProvider googleContextProvider;

    @Autowired
    private ConfigurationService configurationService;

    @Override
    public DriveDto all() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(OAuth2GoogleService.AUTHORIZATION, "");
        Request.Builder<DriveDto> request = new Request.Builder<>(GET, RestResource.DRIVE_GET_FILES.getPath());
        DriveDto result = execute(request.responseClass(DriveDto.class).headers(headers).build());
        return result;
    }

    @Override
    public void upload(MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

        headers.add(OAuth2GoogleService.AUTHORIZATION, "");
        headers.add("accept", "*/*");
        headers.add("Content-Type", "application/octet-stream");

        byte [] body = null;
        try {
             body = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.add("body", body);
        map.add("title", "test");
        Request.Builder<Object> request = new Request.Builder<>(POST, RestResource.DRIVE_UPLOAD_FILE.getPath());
        execute(request.headers(headers).body(body).responseClass(Object.class).build());

    }

    @Override
    public File get(String fileId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(OAuth2GoogleService.AUTHORIZATION, "");
        Request.Builder<HttpResponse> request = new Request.Builder<>(GET, RestResource.DRIVE_GET_FILE.getPath(fileId));
        HttpResponse result = execute(request.headers(headers).responseClass(HttpResponse.class).build());
        return null;
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
}
