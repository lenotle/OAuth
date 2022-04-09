package com.le.security.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * contextLoaderListener
 *
 * @Auther: xll
 * @Date: 2022/1/14 10:43
 */
@Configuration
@ComponentScan(basePackages = {"com.le.security"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})})
public class ApplicationConfig {
}
