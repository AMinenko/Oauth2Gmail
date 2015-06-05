package com.anmi.mailclient.core.security.oauth;

        import com.anmi.mailclient.web.dto.google.oauth.GoogleAuthTokenDto;
        import org.springframework.stereotype.Component;

@Component
public class GoogleContextProvider {
    private GoogleAuthTokenDto baseTokenDto;
    private String path;
    private String scope;


    public GoogleAuthTokenDto getToken(){
        return baseTokenDto;
    }


    public void setToken(GoogleAuthTokenDto googleAuthTokenDto) {
        this.baseTokenDto=googleAuthTokenDto;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
