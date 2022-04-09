package com.le.security.zuul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Auther: xll
 * @Date: 2022/1/19 10:52
 */
@Configuration
public class JwtConfig {
    private static final String SIGNING_KEY = "xll";

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter c = new JwtAccessTokenConverter();
        c.setSigningKey(SIGNING_KEY);
        return c;
    }
}
