package com.le.oauth2.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.le.oauth2.common.security.filter","com.le.oauth2.product.*"})
public class OAuthCloudProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuthCloudProductApplication.class);
    }
}