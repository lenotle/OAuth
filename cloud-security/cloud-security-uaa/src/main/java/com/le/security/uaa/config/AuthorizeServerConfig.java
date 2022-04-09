package com.le.security.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * 授权服务配置
 *
 * @Auther: xll
 * @Date: 2022/1/18 8:43
 */
@Configuration
@EnableAuthorizationServer // 开启授权
public class AuthorizeServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager; // 认证管理器，password类型时使用

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource) {
        ClientDetailsService service = new JdbcClientDetailsService(dataSource);
        ((JdbcClientDetailsService) service).setPasswordEncoder(passwordEncoder);
        return service;
    }

    // 配置客户端详情
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("c1")
//                .secret(passwordEncoder.encode("secret"))
//                // 客户单访问范围，若为空则拥有全部
//                .scopes("all")
//                .resourceIds("res1")
//                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
//                 // 客户端 手动点击授权
//                .autoApprove(false)
//                 // 回调地址
//                .redirectUris("http://www.baidu.com")
//        ;
        // 从数据库查
        clients.withClientDetails(clientDetailsService);
    }

    // 配置令牌管理服务
    @Bean
    public AuthorizationServerTokenServices authorizationServerTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setClientDetailsService(clientDetailsService); // 客户端详情
        tokenServices.setTokenStore(tokenStore);    // 令牌存储策略
        tokenServices.setReuseRefreshToken(true);   // 是否支持刷新token
        // 令牌增强 支持jwt
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter));
        tokenServices.setTokenEnhancer(tokenEnhancerChain);

        tokenServices.setAccessTokenValiditySeconds(7200);  // 令牌默认有效期2小时
        tokenServices.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天
        return tokenServices;
    }

    // 授权码模式
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
//        return new InMemoryAuthorizationCodeServices();
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    // 配置令牌访问端点及令牌服务
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)    // 授权管理器 password
                .authorizationCodeServices(authorizationCodeServices)     // 授权码模式
                .tokenServices(authorizationServerTokenServices())   // 令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST)

        ;
    }

    // 配置令牌端点安全策略
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")      // /oauth/token_key 获取公钥
                .checkTokenAccess("permitAll()")    // 可以验证token
                .allowFormAuthenticationForClients()    // 表单验证
        ;
    }
}
