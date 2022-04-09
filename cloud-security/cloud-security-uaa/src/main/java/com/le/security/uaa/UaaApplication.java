package com.le.security.uaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Auther: xll
 * @Date: 2022/1/17 9:37
 */
//@EnableFeignClients(basePackages = {"com.le.security.uaa"})
//@EnableHystrix
@EnableEurekaClient
@SpringBootApplication
public class UaaApplication {
    public static void main(String[] args) {
        SpringApplication.run(UaaApplication.class);
    }
}
