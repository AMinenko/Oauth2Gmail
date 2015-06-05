package com.anmi.mailclient.core.security.oauth;

import com.anmi.mailclient.core.configuration.ConfigurationProperties;
import com.anmi.mailclient.core.configuration.ConfigurationService;
import com.anmi.mailclient.core.rest.Request;
import com.anmi.mailclient.core.rest.RestBasedAPIService;
import com.anmi.mailclient.web.dto.google.oauth.GoogleAuthTokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class OAuth2GoogleService extends RestBasedAPIService {

    @Autowired
    private ConfigurationService configurationService;

    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String GRANT_TYPE = "grant_type";
    public static final String REDIRECT_URI = "redirect_uri";
    public static final String RESPONSE_CODE = "code";
    public static final String SCOPE = "scope";
    public static final String AUTHORIZATION = "authorization";


    @Override
    public String getAuthorizationToken() {
        return null;
    }


    public GoogleAuthTokenDto getAuthToken(String code) {

        String url = configurationService.getProperty(ConfigurationProperties.OAUTH_TOKEN_URL);
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.set(CLIENT_ID, configurationService.getProperty(ConfigurationProperties.OAUTH_CLIENT_ID));
        requestBody.set(CLIENT_SECRET, configurationService.getProperty(ConfigurationProperties.OAUTH_CLIENT_SECRET));
        requestBody.set(REDIRECT_URI, configurationService.getProperty(ConfigurationProperties.SITE_URL));
        requestBody.set(GRANT_TYPE, configurationService.getProperty(ConfigurationProperties.OAUTH_GRANT_TYPE));
        requestBody.set(SCOPE, configurationService.getProperty(ConfigurationProperties.OAUTH_GMAIL_SCOPE));
        requestBody.set(RESPONSE_CODE, code);

        Request.Builder<GoogleAuthTokenDto> requestBuilder = new Request.Builder<>(HttpMethod.POST);

        return execute(requestBuilder.body(requestBody).responseClass(GoogleAuthTokenDto.class).anonymous().uri(url).build());
    }

}
