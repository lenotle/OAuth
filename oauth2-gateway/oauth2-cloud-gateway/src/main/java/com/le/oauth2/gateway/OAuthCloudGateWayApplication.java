package com.le.oauth2.gateway;

import com.le.oauth2.gateway.model.SysParameterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties(value = {SysParameterConfig.class})
public class OAuthCloudGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuthCloudGateWayApplication.class);
    }
}
