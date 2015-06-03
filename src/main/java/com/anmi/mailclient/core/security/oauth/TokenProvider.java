package com.anmi.mailclient.core.security.oauth;

        import com.anmi.mailclient.web.dto.BaseTokenDto;
        import com.anmi.mailclient.web.dto.google.oauth.GoogleAuthTokenDto;
        import org.springframework.stereotype.Component;

@Component
public class TokenProvider {
    private BaseTokenDto baseTokenDto;
    private ThreadLocal<BaseTokenDto> tokenLocal = new ThreadLocal<>();


    public BaseTokenDto getToken(){
      //  BaseTokenDto baseTokenDto = tokenLocal.get();
        return baseTokenDto;
    }


    public void setToken(GoogleAuthTokenDto googleAuthTokenDto) {
        this.baseTokenDto=googleAuthTokenDto;
    }
}
