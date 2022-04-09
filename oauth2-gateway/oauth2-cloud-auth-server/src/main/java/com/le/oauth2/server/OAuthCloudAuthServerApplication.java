package com.le.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Auther: xll
 * @Date: 2022/1/20 11:02
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAspectJAutoProxy
public class OAuthCloudAuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuthCloudAuthServerApplication.class);
    }
}
