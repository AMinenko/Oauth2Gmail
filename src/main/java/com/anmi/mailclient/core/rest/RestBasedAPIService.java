package com.anmi.mailclient.core.rest;

import com.anmi.mailclient.core.configuration.ConfigurationService;
import com.anmi.mailclient.core.security.oauth.OAuth2GoogleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;


public abstract class RestBasedAPIService {
    private static final Logger LOG = LoggerFactory.getLogger(RestBasedAPIService.class);

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private ConfigurationService configurationService;


    protected final <R> R execute(Request<R> request) {
        String uri = request.getUri();
        HttpMethod method = request.getMethod();
        HttpHeaders headers = request.getHeaders();
        if (headers.containsKey(OAuth2GoogleService.AUTHORIZATION)) {
            String authToken = getAuthorizationToken();
            if (!authToken.isEmpty()) {
                headers.set(OAuth2GoogleService.AUTHORIZATION, authToken);
            }
        }
        LOG.debug("Executing '{}' request to '{}'", method, uri);
        HttpEntity httpEntity = new HttpEntity<>(request.getBody(), headers);
        return (R) restTemplate.exchange(uri, method, httpEntity, request.getResponseClass()).getBody();
    }

    protected abstract String getAuthorizationToken();

   /* public String getUrl() {
        return configurationService.getProperty(urlProperty);
    }
*/


}
