package com.le.oauth2.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.le.oauth2.common.security.filter","com.le.oauth2.order.*"})
public class OAuthCloudOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuthCloudOrderApplication.class);
    }
}
